package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Coins extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

    @ApiModelProperty("流水号")
    private String key;

    @ApiModelProperty("账变类型")
    private String r; //加钱原因

    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("余额")
    private Long curr;//改变之后的金额

    @ApiModelProperty("时间")
    private String time;

    @ApiModelProperty("账变金额")
    private Long value;// 改变数量

    @ApiModelProperty("账变前金额")
    private Long before;//之前的金额

    @ApiModelProperty("所属渠道")
    private String channel;

    @ApiModelProperty("游戏类型")
    private Long gameType;

    @ApiModelProperty("桌号")
    private Long tableType;

    @ApiModelProperty("设备名称")
    private String deviceBrand;

    @ApiModelProperty("玩家名称")
    private String uname;

    @ApiModelProperty("游戏名称")
    private String gameName;

    @ApiModelProperty("账变类型名称")
    private String rname; //加钱原因

    private Long safeBox;//保险箱余额
}
