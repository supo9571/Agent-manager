package com.manager.common.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录对象
 *
 * @author marvin
 */
@Data
public class LoginBody
{
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required=true,position = 0)
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "密码",required=true,position = 1)
    private String password;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "谷歌验证码",required=true,position = 2)
    private String googleCode;
}
