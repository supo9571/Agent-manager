package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/20
 */
@Data
public class ControlPlayerInfo extends BaseEntity {

    @ApiModelProperty("日志id")
    private int id;

    @ApiModelProperty("玩家id")
    private Long uid;

    @ApiModelProperty(hidden = true)
    private Integer tid;

    @ApiModelProperty("修改项 1添加风控 2修改风控类型 3修改风控强度 4修改风控等级 5修改备注信息 6移除风控")
    private int updateType;

    @ApiModelProperty("修改前")
    private String updateBefore;

    @ApiModelProperty("修改后")
    private String updateAfter;

}
