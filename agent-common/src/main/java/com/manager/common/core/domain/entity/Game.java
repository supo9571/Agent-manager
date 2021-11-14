package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 子游戏管理
 *
 * @author sieGuang 2021/09/03
 */
@Data
public class Game extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tId;

    @ApiModelProperty("游戏id")
    private String gameId;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("游戏名")
    private String gameName;

    @ApiModelProperty("游戏类型 1：百人类 2：对战类 3：单人类 4 其他")
    private String gameType;

    @ApiModelProperty("状态[0:正常][1:期待][2:维护][3:关闭]")
    private String status;

    @ApiModelProperty("游戏属性  0=无 1=推荐,2=热门 3=火爆 4=最新")
    private int gameBs;

    @ApiModelProperty("客户端排序")
    private int userSort;

}
