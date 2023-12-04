package com.zmark.mytodo.dto.task;

import com.zmark.mytodo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于TaskService传递数据的DTO
 *
 * @author ZMark
 * @date 2023/12/4 10:52
 * @see Task 对应的实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer id;

    private String title;

    private Boolean completed;

    private Timestamp completedTime;

    private Boolean archived;

    private List<Tag> tags;

    private TaskContentInfo taskContentInfo;

    private TaskPriorityInfo taskPriorityInfo;

    private TaskTimeInfo taskTimeInfo;

    private Timestamp createTime;

    private Timestamp updateTime;

    public static TaskDTO from(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .completed(task.getCompleted())
                .completedTime(task.getCompletedTime())
                .archived(task.getArchived())
                .tags(new ArrayList<>())
                .taskContentInfo(task.getTaskContentInfo())
                .taskPriorityInfo(task.getTaskPriorityInfo())
                .taskTimeInfo(task.getTaskTimeInfo())
                .createTime(task.getCreateTime())
                .updateTime(task.getUpdateTime())
                .build();
    }

    public static TaskDTO from(Task task, List<Tag> tags) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .completed(task.getCompleted())
                .completedTime(task.getCompletedTime())
                .archived(task.getArchived())
                .tags(tags)
                .taskContentInfo(task.getTaskContentInfo())
                .taskPriorityInfo(task.getTaskPriorityInfo())
                .taskTimeInfo(task.getTaskTimeInfo())
                .createTime(task.getCreateTime())
                .updateTime(task.getUpdateTime())
                .build();
    }
}
