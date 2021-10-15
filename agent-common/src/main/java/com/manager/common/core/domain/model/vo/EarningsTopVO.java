package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-10-12
 */
@Data
public class EarningsTopVO {

    @Excel(name = "渠道号")
    private String channel;

    @Excel(name = "玩家ID")
    private Integer uid;

    @Excel(name = "玩家昵称")
    private String name;

    @Excel(name = "注册时间")
    private String time;

    @Excel(name = "玩家净盈利", isStatistics = true)
    private BigDecimal amount;

    @Excel(name = "投注流水", isStatistics = true)
    private Long betTotal;

    @Excel(name = "充值金额", isStatistics = true)
    private BigDecimal rechargeAmount;

    @Excel(name = "提现金额", isStatistics = true)
    private BigDecimal withdrawAmount;

    @Excel(name = "最后付费时间")
    private String lastPayTime;

    @Excel(name = "最后登录时间")
    private String lastLoginTime;

    @Excel(name = "常玩游戏")
    private String gameNames;

}
