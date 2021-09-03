package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/20
 */
@Data
public class OnlinePlayer extends BaseEntity {

    @ApiModelProperty("玩家id")
    private Long uid;

    @ApiModelProperty("玩家昵称")
    private String name;

    @ApiModelProperty("游戏id")
    private int gameType;

    @ApiModelProperty("游戏名称")
    private int gameName;

    @ApiModelProperty("桌号")
    private int tableType;

    @ApiModelProperty("携带金额")
    private Long coins;

    @ApiModelProperty("保险箱金额")
    private Long safeBox;

    @ApiModelProperty("所属渠道")
    private Long channel;

    @ApiModelProperty("注册时间")
    private String time;

    @ApiModelProperty("注册ip")
    private String registerIp;

    @ApiModelProperty("最后登录ip")
    private String loginIp;

    @ApiModelProperty("最后登录机器码")
    private String loginDeviceId;

    @ApiModelProperty("今日充值")
    private String todayAdd;

    @ApiModelProperty("今日提现")
    private String todayRed;
}
