package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-10-16
 */
@Data
public class AgentTopVO {

    @Excel(name = "渠道号")
    private String channel;

    @Excel(name = "玩家ID")
    private Integer uid;

    @Excel(name = "玩家昵称")
    private String name;

    @Excel(name = "注册时间")
    private String time;

    @Excel(name = "直属下级人数", isStatistics = true)
    private Integer subNum;

    @Excel(name = "累计佣金收益", isStatistics = true)
    private BigDecimal totalIncome;

    @Excel(name = "累计已提佣金", isStatistics = true)
    private BigDecimal cashIncome;

}
