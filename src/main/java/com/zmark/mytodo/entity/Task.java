package com.zmark.mytodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zmark.mytodo.utils.TimeUtils;
import com.zmark.mytodo.vo.task.req.TaskCreatReq;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task")
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "completed")
    @Builder.Default
    private Boolean completed = false;

    @Column(nullable = true, name = "completed_time")
    @Builder.Default
    private Timestamp completedTime = null;

    @Column(nullable = false, name = "list_id")
    @Builder.Default
    private Long taskListId = 1L;

    @Column(nullable = false, name = "archived")
    private Boolean archived;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private TaskContentInfo taskContentInfo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private TaskPriorityInfo taskPriorityInfo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "task", orphanRemoval = true)
    private TaskTimeInfo taskTimeInfo;

    @Column(nullable = false, name = "create_time")
    @Builder.Default
    private Timestamp createTime = TimeUtils.now();

    @Column(nullable = false, name = "update_time")
    @Builder.Default
    private Timestamp updateTime = TimeUtils.now();

    public void complete() {
        this.completed = true;
        this.completedTime = new Timestamp(System.currentTimeMillis());
    }

    public void unComplete() {
        this.completed = false;
        this.completedTime = null;
    }

    public static Task fromTaskCreatReq(TaskCreatReq req) {
        TaskPriorityInfo taskPriorityInfo = TaskPriorityInfo.builder()
                .isImportant(req.getIsImportant() != null && req.getIsImportant())
                .isUrgent(req.getIsUrgent() != null && req.getIsUrgent())
                .build();
        TaskContentInfo taskContentInfo = TaskContentInfo.builder()
                .description(req.getDescription())
                .build();
        TaskTimeInfo taskTimeInfo = TaskTimeInfo.builder()
                .endDate(req.getEndDate())
                .endTime(req.getEndTime())
                .activateCountdown(req.getActivateCountdown() != null && req.getActivateCountdown())
                .expectedExecutionDate(req.getExpectedExecutionDate())
                .expectedExecutionStartPeriod(req.getExpectedExecutionStartPeriod())
                .expectedExecutionEndPeriod(req.getExpectedExecutionEndPeriod())
                .build();
        Task task = Task.builder()
                .title(req.getTitle())
                .completed(false)
                .completedTime(null)
                .archived(false)
                .taskContentInfo(taskContentInfo)
                .taskPriorityInfo(taskPriorityInfo)
                .taskTimeInfo(taskTimeInfo)
                .build();
        taskContentInfo.setTask(task);
        taskPriorityInfo.setTask(task);
        taskTimeInfo.setTask(task);
        return task;
    }
}
