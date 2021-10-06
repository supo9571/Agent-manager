package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/10/4
 */
@Data
public class Summarize extends BaseEntity {

    //游戏输赢
    private BigDecimal systemWin;

    //新增玩家
    private Long newNum;

    //活跃玩家
    private Integer activeNum;

    //充值人数
    private Long rechargeNum;

    //充值金额
    private BigDecimal rechargeCount;

    //新增充值玩家
    private Long newRechargeNum;

    //提现人数
    private Long exchangeNum;

    //提现金额
    private BigDecimal exchangeCount;

    //实际提现金额
    private BigDecimal actualExchangeCount;

    //赠送人数
    private Long giveNum;

    //赠送金额
    private BigDecimal giveCount;

    //已没收金额
    private BigDecimal confiscateCount;

    //新增充值玩家 充值金额
    private BigDecimal newRechargeCount;

    //新增充值玩家 提现金额
    private BigDecimal newExchangeCount;

    //新增充值玩家 流水
    private BigDecimal newWater;

    //投注金额
    private BigDecimal betCount;

    //返奖金额
    private BigDecimal rewardCount;

    //游戏税收
    private BigDecimal feeCount;

    //游戏业绩
    private BigDecimal performanceCount;

    //代理直属返佣
    private BigDecimal commissionSubCount;

    //代理团队返佣
    private BigDecimal commissionTeamCount;

    //当日余额
    private BigDecimal balanceCount;

    //充值赠送金额
    private BigDecimal rechargeGiveCount;

    //线下赠送金额
    private BigDecimal offlineGiveCount;

    //活动奖励
    private BigDecimal activityCount;

    //绑定赠送
    private BigDecimal bindGiveCount;

    //vip等级奖励
    private BigDecimal vipLevelCount;

    //vip礼金
    private BigDecimal vipCount;

    //救济金
    private BigDecimal almsCount;

    //线上支付成功率
    private double onlinePayRate;

    //线下支付金额占比
    private double offlinePayRate;

    //赢家人数占比
    private double winRate;

    //流水倍数
    private double waterRate;

    //日期
    private String day;

    //渠道
    private String channel;

}
