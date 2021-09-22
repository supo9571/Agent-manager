package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jason
 * @date 2021-09-20
 */
@Data
public class ConfigLanding extends BaseEntity {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("平台ID")
    private Integer tid;

    @ApiModelProperty("安卓包地址")
    private String androidUrl;

    @ApiModelProperty("超级签地址")
    private String superSignUrl;

    @ApiModelProperty("超级签状态：[1启用][2:禁用]")
    private Integer superSignStatus;

    @ApiModelProperty("企业签地址")
    private String enterpriseSignUrl;

    @ApiModelProperty("企业签状态：[1启用][2:禁用]")
    private Integer enterpriseSignStatus;

    @ApiModelProperty("TF签地址")
    private String tfSignUrl;

    @ApiModelProperty("TF签状态：[1启用][2:禁用]")
    private Integer tfSignStatus;

    @ApiModelProperty("客服链接地址")
    private String customerUrl;

}
