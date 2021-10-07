package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Data
public class SubGameData extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("游戏id")
    private String gameId;

}
