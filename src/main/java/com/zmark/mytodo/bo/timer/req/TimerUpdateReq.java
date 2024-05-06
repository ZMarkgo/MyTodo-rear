package com.zmark.mytodo.bo.timer.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Violette
 * @date 2024/5/5 0:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimerUpdateReq {
    @NotNull(message = "计时器id必填")
    private Long id;

    @NotNull(message = "计时器结束时间必填")
    @NotEmpty(message = "计时器结束时间不能为空")
    private String endTime; // "2024-05-05 15:00:00"的timestamp格式
}