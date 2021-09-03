package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/25
 */
@Data
public class SysBlack extends BaseEntity {

    private Long id;
    private String tid;
    @ApiModelProperty("黑名单类型 1:ip 2:支付宝 3:银行卡 4:设备号")
    private Integer blackType;
    @ApiModelProperty("处理方式 1:预警 2:无法提现 3:封号")
    private Integer handleType;
    private String blackNum;

}
