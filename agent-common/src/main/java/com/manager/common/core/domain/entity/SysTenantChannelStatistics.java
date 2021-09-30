package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jason
 * @date 2021-09-30
 */
@Data
public class SysTenantChannelStatistics extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日期")
    private String day;

    @ApiModelProperty("渠道号")
    private String tId;

    @ApiModelProperty("总盈亏")
    private String totalAmount;

    @ApiModelProperty("充值人数")
    private Long rechargeNum;

    @ApiModelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("提现人数")
    private Long withdrawNum;

    @ApiModelProperty("提现金额")
    private String withdrawAmount;

    @ApiModelProperty("上赠送金额")
    private BigDecimal upAwardAmount;

    @ApiModelProperty("线下赠送金额")
    private BigDecimal lowerAwardAmount;

    @ApiModelProperty("代理直属返佣")
    private BigDecimal underCommission;

    @ApiModelProperty("代理团队返佣")
    private BigDecimal agentCommission;

    @ApiModelProperty("安装量")
    private Long installNum;

    @ApiModelProperty("新增绑定")
    private Long increasedBinding;

    @ApiModelProperty("活跃人数")
    private Long activeNum;

    @ApiModelProperty("新增人数")
    private Long increasedNum;

    @ApiModelProperty("首充人数")
    private Long rechargeFirst;

    @ApiModelProperty("首充金额")
    private Long rechargeAmountFirst;

    @ApiModelProperty("新增玩家充值人数")
    private Long rechargeIncreasedNum;

    @ApiModelProperty("新增玩家充值金额")
    private Long rechargeIncreasedAmount;

    @ApiModelProperty("充值提现差额")
    private Long topUpWithdrawal;

    @ApiModelProperty("appu")
    private BigDecimal appu;

    @ApiModelProperty("arpu")
    private BigDecimal arpu;

    @ApiModelProperty("投注金额")
    private BigDecimal betAmount;

    @ApiModelProperty("返奖金额")
    private BigDecimal rebate;

    @ApiModelProperty("返奖金额")
    private Long performance;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}
