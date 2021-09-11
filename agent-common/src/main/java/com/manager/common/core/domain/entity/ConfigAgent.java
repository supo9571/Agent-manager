package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author andy 2021/9/7
 */
@Data
public class ConfigAgent implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("代理级别")
    private Integer lvl;

    @ApiModelProperty("代理名称")
    private String lvlName;

    @ApiModelProperty("流水区间下限")
    private long min;

    @ApiModelProperty("流水区间上限")
    private long max;

    @ApiModelProperty("每万返佣额度")
    private Integer rebate;

    @ApiModelProperty("推广链接域名配置")
    private String promotionDomain;
}
