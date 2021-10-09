package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/09
 */
@Data
public class PlayWater extends BaseEntity {

    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("账变类型")
    private String r; //加钱原因

    @ApiModelProperty("游戏类型")
    private Long gameType;
}
