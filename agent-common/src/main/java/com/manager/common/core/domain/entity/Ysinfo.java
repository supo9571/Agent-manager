package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/6
 * 银商 信息 config_ys
 */
@Data
public class Ysinfo extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(hidden = true)
    private String ipWhiteList;

    @ApiModelProperty("google验证码开关 1开 2关")
    private String googleCheck;

    @ApiModelProperty("google密钥")
    private String googleKey;

    @ApiModelProperty("充值功能开关 1开 2关")
    private String rechargeStatus;

    @ApiModelProperty("余额")
    private Integer amount;

    @ApiModelProperty(hidden = true)
    private String status;


}
