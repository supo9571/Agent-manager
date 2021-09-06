package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/6
 */
@Data
public class OnlineRecharge extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("商城页签id")
    private Integer payid;

    @ApiModelProperty("支付渠道")
    private String payType;

    @ApiModelProperty("通道层级")
    private String vipList;

    @ApiModelProperty("通道别名")
    private String otherName;

    @ApiModelProperty("是否自定义 1固定金额 2自定义")
    private String isDiy;

    @ApiModelProperty("自定义最小金额")
    private String diyMin;

    @ApiModelProperty("自定义最大金额")
    private String diyMax;

    @ApiModelProperty("固定金额按钮")
    private String btn;

    @ApiModelProperty("支付调用地址")
    private String payUrl;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty("玩家渠道")
    private String channelList;

    @ApiModelProperty("使用手机类型")
    private String useMobile;

    @ApiModelProperty("状态 1开启 2关闭")
    private String status;

    @ApiModelProperty("温馨提示")
    private String msg;
}
