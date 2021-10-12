package com.manager.common.core.domain.model.vo;

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
    private String channel;

    @ApiModelProperty("玩家id")
    private Integer uid;

    @ApiModelProperty("玩家昵称")
    private String name;

    @ApiModelProperty("注册时间")
    private String time;

    @ApiModelProperty("流水(总下注)")
    private BigDecimal betTotal;

    @ApiModelProperty("牌局数")
    private Integer gameTableNum;

    @ApiModelProperty("历史累计充值")
    private BigDecimal rechargeAmountTotal;

    @ApiModelProperty("历史累计提现")
    private BigDecimal withdrawAmountTotal;


}
