package com.manager.common.core.domain.model;

import com.manager.common.annotation.Excel;
import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/10/4
 */
@Data
public class Summarize extends BaseEntity {

    //游戏输赢
    @Excel(name = "游戏输赢",sort = 2)
    private BigDecimal systemWin;

    //新增玩家
    @Excel(name = "新增玩家",sort = 3)
    private Long newNum;

    //活跃玩家
    @Excel(name = "活跃玩家",sort = 4)
    private Integer activeNum;

    //充值人数
    @Excel(name = "充值人数",sort = 5)
    private Long rechargeNum;

    //充值金额
    @Excel(name = "充值金额",sort = 6)
    private BigDecimal rechargeCount;

    //新增充值玩家
    @Excel(name = "新增充值玩家",sort = 7)
    private Long newRechargeNum;

    //提现人数
    @Excel(name = "提现人数",sort = 8)
    private Long exchangeNum;

    //提现金额
    @Excel(name = "提现金额",sort = 9)
    private BigDecimal exchangeCount;

    //实际提现金额
    @Excel(name = "实际提现金额",sort = 10)
    private BigDecimal actualExchangeCount;

    //赠送人数
    @Excel(name = "赠送人数",sort = 11)
    private Long giveNum;

    //赠送金额
    @Excel(name = "赠送金额",sort = 12)
    private BigDecimal giveCount;

    //已没收金额
    @Excel(name = "已没收金额",sort = 13)
    private BigDecimal confiscateCount;

    //新增充值玩家 充值金额
    @Excel(name = "新增充值玩家 充值金额",sort = 14)
    private BigDecimal newRechargeCount;

    //新增充值玩家 提现金额
    @Excel(name = "新增充值玩家 提现金额",sort = 15)
    private BigDecimal newExchangeCount;

    //新增充值玩家 流水
    @Excel(name = "新增充值玩家 流水",sort = 16)
    private BigDecimal newWater;

    //投注金额
    @Excel(name = "投注金额",sort = 17)
    private BigDecimal betCount;

    //返奖金额
    @Excel(name = "返奖金额",sort = 18)
    private BigDecimal rewardCount;

    //游戏税收
    @Excel(name = "游戏税收",sort = 19)
    private BigDecimal feeCount;

    //游戏业绩
    @Excel(name = "游戏业绩",sort = 20)
    private BigDecimal performanceCount;

    //代理直属返佣
    @Excel(name = "代理直属返佣",sort = 21)
    private BigDecimal commissionSubCount;

    //代理团队返佣
    @Excel(name = "代理团队返佣",sort = 22)
    private BigDecimal commissionTeamCount;

    //当日余额
    @Excel(name = "当日余额",sort = 23)
    private BigDecimal balanceCount;

    //充值赠送金额
    @Excel(name = "充值赠送金额",sort = 24)
    private BigDecimal rechargeGiveCount;

    //线下赠送金额
    @Excel(name = "线下赠送金额",sort = 25)
    private BigDecimal offlineGiveCount;

    //活动奖励
    @Excel(name = "活动奖励",sort = 26)
    private BigDecimal activityCount;

    //绑定赠送
    @Excel(name = "绑定赠送",sort = 27)
    private BigDecimal bindGiveCount;

    //vip等级奖励
    @Excel(name = "vip等级奖励",sort = 28)
    private BigDecimal vipLevelCount;

    //vip礼金
    @Excel(name = "vip礼金",sort = 29)
    private BigDecimal vipCount;

    //救济金
    @Excel(name = "救济金",sort = 30)
    private BigDecimal almsCount;

    //线上支付成功率
    @Excel(name = "线上支付成功率",sort = 31)
    private double onlinePayRate;

    //线下支付金额占比
    @Excel(name = "线下支付金额占比",sort = 32)
    private double offlinePayRate;

    //赢家人数占比
    @Excel(name = "赢家人数占比",sort = 33)
    private double winRate;

    //流水倍数
    @Excel(name = "流水倍数",sort = 34)
    private double waterRate;

    //日期
    @Excel(name = "日期",sort = 1)
    private String day;

    //渠道
    private String channel;

    //平台 渠道
    private String tid;

    //渠道
    private Long uid;
}
