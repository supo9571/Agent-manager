package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/09
 */
@Data
public class UserLock extends BaseEntity {

    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("封号类型 0 不清币 1清币 2 解封")
    private int lockType;

    @ApiModelProperty("封号天数")
    private int lockDay;

    @ApiModelProperty("原因备注")
    private String lockMark;
}
