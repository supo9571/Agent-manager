package com.manager.system.domain;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.annotation.Excel;
import com.manager.common.annotation.Excel.ColumnType;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author marvin
 */
@Data
public class SysLogininfor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    @ApiModelProperty(value = "ID")
    private Long infoId;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    @ApiModelProperty(value = "账号名")
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String status;

    /**
     * 登录IP地址
     */
    @Excel(name = "登录地址")
    @ApiModelProperty(value = "ip地址")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Excel(name = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器")
    @ApiModelProperty(value = "登录设备")
    private String browser;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String os;

    /**
     * 提示消息
     */
    @Excel(name = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "登录时间")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    @ApiModelProperty(value = "平台id")
    private String tid;

}
