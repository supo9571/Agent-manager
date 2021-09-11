package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 提现信息管理
 * @author sieGuang 2021/09/07
 */
@Data
public class Exchange extends BaseEntity {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("兑换方式 0支付宝 1银行卡")
    private int method;

    @ApiModelProperty("当天最多提现金额 单位：分")
    private int countMax;

    @ApiModelProperty("最小兑换金额")
    private String minMoney;

    @ApiModelProperty("最大兑换金额")
    private String maxMoney;

    @ApiModelProperty("状态 1启用 2禁用")
    private String status;

    @ApiModelProperty("手续费 百分比")
    private int poundage;

    @ApiModelProperty("账户保留金额")
    private String keepMoney;

    @ApiModelProperty("提现次数")
    private int num;

    @ApiModelProperty("打码倍数")
    private int addMosaicNum;

    @ApiModelProperty("基础类型 1保留金额  2打码倍数 3提现总次数")
    private String settingsType;

}
