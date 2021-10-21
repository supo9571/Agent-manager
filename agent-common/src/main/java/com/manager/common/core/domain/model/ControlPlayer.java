package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/10/20
 */
@Data
public class ControlPlayer extends BaseEntity {

    @ApiModelProperty("玩家id")
    private Long uid;

    @ApiModelProperty("风控类型 1=杀,2=送")
    private Integer type;

    @ApiModelProperty("风控强度")
    private Integer riskPower;

    @ApiModelProperty("风控等级")
    private Integer sendLevel;

    @ApiModelProperty("备注")
    private String mark;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("玩家投注")
    private BigDecimal betCoins;

    @ApiModelProperty("玩家盈亏")
    private BigDecimal addScore;

    @ApiModelProperty("玩家返奖")
    private BigDecimal reward;
}
