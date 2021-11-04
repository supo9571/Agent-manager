package com.manager.common.core.domain.entity;

import com.manager.common.annotation.Excel;
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

    @ApiModelProperty("渠道号")
    @Excel(name = "平台号")
    private String tId;

    @ApiModelProperty("日期(年月日)")
    private String day;

    @ApiModelProperty("总盈亏")
    @Excel(name = "渠道盈亏")
    private String totalAmount;

    @ApiModelProperty("充值人数")
    @Excel(name = "充值人数")
    private int rechargeNum;

    @ApiModelProperty("充值金额")
    @Excel(name = "充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("提现人数")
    @Excel(name = "提现人数")
    private int withdrawNum;

    @ApiModelProperty("提现金额")
    @Excel(name = "提现金额")
    private BigDecimal withdrawAmount;

    @ApiModelProperty("上赠送金额")
    @Excel(name = "上赠送金额")
    private BigDecimal upAwardAmount;

    @ApiModelProperty("线下赠送金额")
    @Excel(name = "线下赠送金额")
    private BigDecimal lowerAwardAmount;

    @ApiModelProperty("代理直属返佣")
    @Excel(name = "代理直属返佣")
    private BigDecimal underCommission;

    @ApiModelProperty("代理团队返佣")
    @Excel(name = "代理团队返佣")
    private BigDecimal agentCommission;

    @ApiModelProperty("安装量")
    @Excel(name = "安装量")
    private int installNum;

    @ApiModelProperty("新增绑定")
    @Excel(name = "新增绑定")
    private int increasedBinding;

    @ApiModelProperty("活跃人数")
    @Excel(name = "活跃人数")
    private int activeNum;

    @ApiModelProperty("新增人数")
    @Excel(name = "新增人数")
    private int increasedNum;

    @ApiModelProperty("首充人数")
    @Excel(name = "首充人数")
    private int rechargeFirst;

    @ApiModelProperty("首充金额")
    @Excel(name = "首充金额")
    private BigDecimal rechargeAmountFirst;

    @ApiModelProperty("新增玩家充值人数")
    @Excel(name = "新增玩家充值人数")
    private int rechargeIncreasedNum;

    @ApiModelProperty("新增玩家充值金额")
    @Excel(name = "新增玩家充值金额")
    private BigDecimal rechargeIncreasedAmount;

    @ApiModelProperty("充值提现差额")
    @Excel(name = "充值提现差额")
    private BigDecimal topUpWithdrawal;

    @ApiModelProperty("appu")
    @Excel(name = "appu")
    private BigDecimal appu;

    @ApiModelProperty("arpu")
    @Excel(name = "arpu")
    private BigDecimal arpu;

    @ApiModelProperty("投注金额")
    @Excel(name = "投注金额")
    private BigDecimal betAmount;

    @ApiModelProperty("返奖金额")
    @Excel(name = "返奖金额")
    private BigDecimal rebate;

    @ApiModelProperty("渠道业绩")
    @Excel(name = "渠道业绩")
    private BigDecimal performance;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}
