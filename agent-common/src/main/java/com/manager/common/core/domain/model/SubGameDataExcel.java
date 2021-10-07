package com.manager.common.core.domain.model;

import com.manager.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Data
public class SubGameDataExcel {

    private String gameId;

    @Excel(name = "游戏名")
    private String gameName;

    @Excel(name = "参与人数",isStatistics=true)
    private int parCount;

    @Excel(name = "人局数",isStatistics=true)
    private int humBurCount;

    @Excel(name = "投注金额",isStatistics=true)
    private BigDecimal betCount;

    @Excel(name = "返奖金额",isStatistics=true)
    private BigDecimal rewardCount;

    @Excel(name = "税收",isStatistics=true)
    private BigDecimal feeCount;

    @Excel(name = "盈亏",isStatistics=true)
    private BigDecimal win;

    @Excel(name = "盈亏比")
    private double winRatio;

    public BigDecimal getWin() {
        //投注金额-返奖金额+税收；
        betCount = betCount==null?new BigDecimal(0):betCount;
        rewardCount = rewardCount==null?new BigDecimal(0):rewardCount;
        feeCount = feeCount==null?new BigDecimal(0):feeCount;
        win = betCount.subtract(rewardCount).add(feeCount);
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public double getWinRatio() {
        // 游戏盈亏/投注金额；
        winRatio = betCount.intValue()==0?0: win.divide(betCount,4).multiply(new BigDecimal(100)).doubleValue();
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }
}
