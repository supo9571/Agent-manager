package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 轮播图配置
 * @author sieGuang 2021/09/23
 */
@Data
public class RotationPicture extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty("轮播图标识")
    private String ident;

    @ApiModelProperty("轮播图描述")
    private String describes;

    @ApiModelProperty("跳转状态 1是 2否")
    private String jumpState;

    @ApiModelProperty("跳转链接")
    private String jumpUrl;

    @ApiModelProperty("状态 1启用 2禁用")
    private String state;

    @ApiModelProperty("扩展信息")
    private String extendInformation;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime1;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime2;

}
