package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/30
 * 热更新 config_package
 */
@Data
public class Hotupdate extends BaseEntity {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("版本号")
    private String version;

    @ApiModelProperty("数字版本号")
    private Integer verInt;

    @ApiModelProperty("允许更新平台id")
    private String allowChannel;

    @ApiModelProperty("不允许更新平台id")
    private String denyChannel;

    @ApiModelProperty("允许更新版本号")
    private String allowVersion;

    @ApiModelProperty("不允许更新版本号")
    private String denyVersion;

    @ApiModelProperty("是否公开，公开：*，不公开：内部ip")
    private String isPublic;

    @ApiModelProperty("游戏配置")
    private String gameInfo;

    @ApiModelProperty("客户端 [1:android][2:ios][3:windows]")
    private String platform;

    @ApiModelProperty("更新状态 [1:启用][2:不启用] ")
    private String status;

    @ApiModelProperty("更新时间")
    private String releaseTime;

    @ApiModelProperty("上传url")
    private String apkUpdateUrl;

    @ApiModelProperty("上传包大小")
    private String pageSize;
}
