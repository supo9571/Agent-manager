package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/30
 * 整包更新 config_update
 */
@Data
public class Allupdate extends BaseEntity {

    @ApiModelProperty("id")
    private int id;

    @ApiModelProperty("平台id")
    private String tid;//平台id

    @ApiModelProperty("更新状态 [1:启用][2:不启用] ")
    private String status;

    @ApiModelProperty("更新版本号")
    private String version;

    @ApiModelProperty("数字版本号")
    private Integer verInt;

    @ApiModelProperty("上传url")
    private String apkUpdateUrl;

    @ApiModelProperty("包大小(kb)")
    private String pageSize;
}
