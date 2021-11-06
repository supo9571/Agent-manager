package com.manager.common.core.domain.entity;

import com.manager.common.annotation.Excel;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
@Data
public class AddUser extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tid;

    private Long tid2;

    @Excel(name = "日期")
    @ApiModelProperty("日期")
    private String day;

    @Excel(name = "新增用户")
    @ApiModelProperty("新增用户")
    private String uid;

    @Excel(name = "新增绑定用户")
    @ApiModelProperty("新增绑定用户")
    private String agentTime;

    @Excel(name = "注册玩牌率")
    @ApiModelProperty("注册玩牌率")
    private double cardPlayingRate;

    public double getCardPlayingRate() {
        return cardPlayingRate;
    }

    public void setCardPlayingRate(double cardPlayingRate) {
        // 保留两位小数
        if(cardPlayingRate != 0){
            BigDecimal b = new BigDecimal(cardPlayingRate);
            this.cardPlayingRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }else{
            this.cardPlayingRate = cardPlayingRate;
        }
    }

    @Excel(name = "新增付费用户数")
    @ApiModelProperty("新增付费用户数")
    private String payUserCount;

    @Excel(name = "新增用户付费率")
    @ApiModelProperty("新增用户付费率")
    private String payUserRate;

    @Excel(name = "新增用户充值金额")
    @ApiModelProperty("新增用户充值金额")
    private String userRechargeMoney;

    @Excel(name = "新增用户提现金额")
    @ApiModelProperty("新增用户提现金额")
    private String userExchangeMoney;

    @Excel(name = "新增arpu")
    @ApiModelProperty("新增arpu")
    private String addArpu;

    @Excel(name = "新增用户牌局数")
    @ApiModelProperty("新增用户牌局数")
    private int userCardCount;

    @Excel(name = "新增用户总投注")
    @ApiModelProperty("新增用户总投注")
    private String userSumBet;

    @Excel(name = "新增用户总派奖")
    @ApiModelProperty("新增用户总派奖")
    private String userReward;

    @Excel(name = "净消耗")
    @ApiModelProperty("净消耗")
    private String consumption;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("创建时间1")
    private String createTime1;

    @ApiModelProperty("创建时间1")
    private String createTime2;

}
