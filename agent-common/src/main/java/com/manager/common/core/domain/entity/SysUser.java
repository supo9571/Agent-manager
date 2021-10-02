package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author marvin
 */
@Data
public class SysUser extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "平台id")
    private String tid;

    @ApiModelProperty(value = "账号状态")
    private String status;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "google验证开关")
    private String googleSwitch;

    @ApiModelProperty(value = "google密钥")
    private String googleKey;

    @ApiModelProperty(value = "IP白名单")
    private String ips;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "最后登录ip")
    private String loginIp;

    @ApiModelProperty(value = "最后登录时间")
    private Date loginDate;

    @ApiModelProperty(value = "删除标识")
    private String delFlag;

    /**
     * 角色对象
     */
    private List<SysRole> roles;

    /**
     * 角色对象
     */
    private String isLoginAdmin;

    public boolean isAdmin() {
        return "admin".equals(userName) || userId == 1;
    }

    public static boolean isAdmin(Long userId) {
        return userId == 1;
    }

    public boolean isSwitchOpen() {
        return "1".equals(googleSwitch);
    }

    public SysUser() {
    }

    public SysUser(long userId) {
        this.userId = userId;
    }
}
