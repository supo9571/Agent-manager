package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
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
    @Excel(name = "渠道号")
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

    @ApiModelProperty("玩家钱包携余额")
    @Excel(name = "当前携带金币", isStatistics = true)
    private BigDecimal amount;

    @ApiModelProperty("提现余额")
    @Excel(name = "提现金额", isStatistics = true)
    private BigDecimal withdrawAmount;

    @ApiModelProperty("充值余额")
    @Excel(name = "充值金额", isStatistics = true)
    private BigDecimal rechargeAmount;

    @ApiModelProperty("历史累计充值")
    @Excel(name = "历史充值", isStatistics = true)
    private BigDecimal rechargeAmountTotal;

    @ApiModelProperty("历史累计提现")
    @Excel(name = "历史提现", isStatistics = true)
    private BigDecimal withdrawAmountTotal;

}
