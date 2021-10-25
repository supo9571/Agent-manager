package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 总代渠道
 */
@Data
public class SysTenantStatistics extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标识：1总代 2渠道")
    private Integer flag;

    @ApiModelProperty("总代/渠道号")
    private String tId;

    @ApiModelProperty("总充提差额")
    private BigDecimal totalAmount;

    @ApiModelProperty("总充值人数")
    private int rechargeNum;

    @ApiModelProperty("总充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty("总提现人数")
    private int withdrawNum;

    @ApiModelProperty("总提现金额")
    private BigDecimal withdrawAmount;

    @ApiModelProperty("线上赠送金额")
    private BigDecimal upAwardAmount;

    @ApiModelProperty("线下赠送金额")
    private BigDecimal lowerAwardAmount;

    @ApiModelProperty("直属返佣")
    private BigDecimal underCommission;

    @ApiModelProperty("代理返佣")
    private BigDecimal agentCommission;

    @ApiModelProperty("总安装量")
    private int installNum;

}