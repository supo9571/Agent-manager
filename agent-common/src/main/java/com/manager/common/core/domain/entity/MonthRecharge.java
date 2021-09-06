package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/6
 */
@Data
public class MonthRecharge extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("金卡充值金额")
    private Integer jinRecharge;

    @ApiModelProperty("金卡充值赠送金额")
    private Integer jinRechargeGive;

    @ApiModelProperty("金卡每日领取金额")
    private Integer jinRechargeGiveDay;

    @ApiModelProperty("银卡充值金额")
    private Integer yinRecharge;

    @ApiModelProperty("银卡充值赠送金额")
    private Integer yinRechargeGive;

    @ApiModelProperty("银卡每日领取金额")
    private Integer yinRechargeGiveDay;

    @ApiModelProperty("跳转方式 1：live800 2 弹窗")
    private Integer openType;

    @ApiModelProperty("live800跳转链接")
    private String liveAddr;

    @ApiModelProperty("微信号")
    private String wechat;

    @ApiModelProperty("QQ号")
    private String qq;

    @ApiModelProperty(hidden = true)
    private String status;


}
