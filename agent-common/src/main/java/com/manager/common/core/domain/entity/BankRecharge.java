package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/6
 */
@Data
public class BankRecharge extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty(hidden = true)
    private Integer payid;

    @ApiModelProperty("通道层级")
    private String vipList;

    @ApiModelProperty("跳转方式 1live800 2银行卡")
    private String openType;

    @ApiModelProperty("支付金额按钮")
    private String btn;

    @ApiModelProperty("live800地址")
    private String liveAddr;

    @ApiModelProperty("银行卡信息")
    private String bankValue;

    @ApiModelProperty("状态 1开启 2关闭")
    private String status;


}
