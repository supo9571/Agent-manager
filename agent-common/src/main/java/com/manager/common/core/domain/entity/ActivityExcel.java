package com.manager.common.core.domain.entity;

import com.manager.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/9/7
 * 活动配置 config_activity
 */
@Data
public class ActivityExcel {

    @Excel(name = "日期")
    private String day;

    @Excel(name = "获奖用户数")
    private int nums;

    @Excel(name = "获奖金额")
    private BigDecimal value;

}
