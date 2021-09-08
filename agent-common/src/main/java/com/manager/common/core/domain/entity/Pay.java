package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商城页签管理
 * @author sieGuang 2021/09/07
 */
@Data
public class Pay extends BaseEntity {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("排序")
    private int sort;

    @ApiModelProperty("中文名称")
    private String cname;

    @ApiModelProperty("充值赠送（百分比）")
    private String rechargeGive;

    @ApiModelProperty("状态 1启用 2停用")
    private String status;

    @ApiModelProperty("支付类型 [1:vip][2:线上][3:银行卡]")
    private int payType;

}
