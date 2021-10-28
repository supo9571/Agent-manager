package com.manager.common.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/8
 * 玩家提现绑定表
 * user_exchange
 */
@Data
public class UserExchange {
    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("支付宝：0 银行卡：1")
    private String type;

    @ApiModelProperty("支付宝/银行卡姓名")
    private String name;

    @ApiModelProperty("支付宝/银行卡号码")
    private String account;

    @ApiModelProperty("银行编码")
    private String originBank;

}
