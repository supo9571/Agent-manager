package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/6
 */
@Data
public class VipRecharge extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(hidden = true)
    private Integer payid;

    @ApiModelProperty("VIP充值名称")
    private String vipName;

    @ApiModelProperty("支付方式 [1:微信][2:支付宝][3:银行卡]多个用逗号分隔")
    private String rechargeType;

    @ApiModelProperty("支付额外方式 [1:花呗][2:信用卡][3:QQ钱包]多个用逗号分隔")
    private String rechargeOtherType;

    @ApiModelProperty("打开方式 1：live800 2：网址：弹窗")
    private String openType;

    @ApiModelProperty("vip头像")
    private String imgType;

    @ApiModelProperty("live地址")
    private String liveAddr;

    @ApiModelProperty("网页地址")
    private String url;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("微信")
    private String wechat;

    @ApiModelProperty("银商")
    private String ysid;

    @ApiModelProperty("状态 1开启 2关闭")
    private String status;
}
