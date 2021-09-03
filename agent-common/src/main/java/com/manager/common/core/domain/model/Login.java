package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/20
 */
@Data
public class Login extends BaseEntity {

    @ApiModelProperty("用户id")
    private String uid;

    @ApiModelProperty("用户昵称")
    private String name;

    @ApiModelProperty("登录ip")
    private String ip;

    @ApiModelProperty("登录时间")
    private String time;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("vip等级")
    private int vipLevel;

    @ApiModelProperty("设备码")
    private String deviceId;

    @ApiModelProperty("设备名")
    private String deviceBrand;

}
