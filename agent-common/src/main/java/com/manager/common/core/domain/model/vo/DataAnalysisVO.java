package com.manager.common.core.domain.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-10-08
 */
@Data
public class DataAnalysisVO {

    @ApiModelProperty("渠道id")
    private String channel;

    @ApiModelProperty("玩家id")
    private Integer uid;

    @ApiModelProperty("玩家昵称")
    private String name;

    @ApiModelProperty("注册时间")
    private String time;

    @ApiModelProperty("玩家钱包携余额")
    private BigDecimal amount;

    @ApiModelProperty("提现余额")
    private BigDecimal withdrawAmount;

    @ApiModelProperty("充值余额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("历史累计充值")
    private BigDecimal rechargeAmountTotal;

    @ApiModelProperty("历史累计提现")
    private BigDecimal withdrawAmountTotal;

}
