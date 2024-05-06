package com.zmark.mytodo.bo.timer.resp;

import com.zmark.mytodo.entity.Timer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Violette
 * @date 2024/5/6 22:52
 * @see Timer 对应的实体类
 * @description 统计本周每天的专注时长
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimerWeekAnalysisResp {
    // Date类型的字符串 : 当日专注时长
    private Map<String, Integer> dayFocusTime;
}