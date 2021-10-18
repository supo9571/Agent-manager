package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import lombok.Data;

/**
 * @author jason
 * @date 2021-10-18
 */
@Data
public class PayInfoVO {

    @Excel(name = "充值额度(区间值)")
    private String amountName;

    @Excel(name = "付费次数")
    private Integer count;

    @Excel(name = "百分比")
    private String percentage;

}
