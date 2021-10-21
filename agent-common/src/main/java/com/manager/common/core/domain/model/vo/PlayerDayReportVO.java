package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-10-11
 */
@Data
public class PlayerDayReportVO {

    @Excel(name = "日期")
    private String day;

    @ApiModelProperty("玩家id")
    @Excel(name = "会员ID")
    private Integer uid;

    @ApiModelProperty("上级代理ID")
    @Excel(name = "上级代理ID")
    private Integer agentId;

    @ApiModelProperty("所属渠道")
    @Excel(name = "所属渠道")
    private String channel;

    @ApiModelProperty("牌局数")
    @Excel(name = "牌局数", isStatistics = true)
    private Integer gameTableNum;

    @ApiModelProperty("投注金额")
    @Excel(name = "投注金额")
    private Integer betCoins;

    @ApiModelProperty("派奖金额")
    @Excel(name = "派奖金额", isStatistics = true)
    private BigDecimal rewardAmount;

    @ApiModelProperty("盈亏")
    @Excel(name = "盈亏", isStatistics = true)
    private BigDecimal addScore;


}
