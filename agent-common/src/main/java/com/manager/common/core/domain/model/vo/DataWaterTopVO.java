package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-10-11
 */
@Data
public class DataWaterTopVO {

    @ApiModelProperty("渠道id")
    @Excel(name = "渠道id")
    private String channel;

    @ApiModelProperty("玩家id")
    @Excel(name = "玩家ID")
    private Integer uid;

    @ApiModelProperty("玩家昵称")
    @Excel(name = "玩家昵称")
    private String name;

    @ApiModelProperty("注册时间")
    @Excel(name = "注册时间")
    private String time;

    @ApiModelProperty("流水(总下注)")
    @Excel(name = "流水", isStatistics = true)
    private BigDecimal betTotal;

    @ApiModelProperty("牌局数")
    @Excel(name = "牌局数", isStatistics = true)
    private Integer gameTableNum;

    @ApiModelProperty("历史充值")
    @Excel(name = "历史充值", isStatistics = true)
    private BigDecimal rechargeAmountTotal;

    @ApiModelProperty("历史提现")
    @Excel(name = "历史提现", isStatistics = true)
    private BigDecimal withdrawAmountTotal;

}
