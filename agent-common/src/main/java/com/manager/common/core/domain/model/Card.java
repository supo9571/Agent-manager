package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Card extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

    @ApiModelProperty("牌局号")
    private String tableGid;

    @ApiModelProperty("游戏id")
    private int gameId;

    @ApiModelProperty("游戏名称")
    private String gameName;

    @ApiModelProperty("房间类型")
    private String tableName;

    @ApiModelProperty("牌局详情")
    private String sideList;

    @ApiModelProperty("玩家id")
    private String uid;
}
