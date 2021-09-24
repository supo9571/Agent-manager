package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author jason
 * @date 2021-09-24
 */
@Data
public class SysTenant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总代号")
    private String tId;

    @ApiModelProperty(value = "父平台id")
    private Integer parentId;

    @ApiModelProperty(value = "祖籍列表")
    private String ancestors;

    @ApiModelProperty(value = "平台名称")
    private String tName;

    @ApiModelProperty(value = "部门状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "类型 0平台 1总代 2渠道")
    private Integer tType;

    @ApiModelProperty(value = "所属平台")
    private Long tenant;

    @ApiModelProperty(value = "备注")
    private String description;

}
