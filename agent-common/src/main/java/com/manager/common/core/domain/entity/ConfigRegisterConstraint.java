package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 注册限制
 * @author jason
 * @date 2021-11-06
 */
@Data
public class ConfigRegisterConstraint extends BaseEntity {

    @ApiModelProperty("平台ID")
    private Integer tid;

    @ApiModelProperty("同ip注册账号最大个数")
    private Integer ipNum;

    @ApiModelProperty("同设备注册账号最大个数")
    private Integer deviceNum;

}
