package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/22
 * 银商 额度记录 config_ys_info
 */
@Data
public class YsQuota extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("银商id")
    private Integer ysid;

    @ApiModelProperty("银商账号")
    private String username;

    @ApiModelProperty("账变金额")
    private Long value;

    @ApiModelProperty("当前余额")
    private Long amount;

    @ApiModelProperty("类型 1上分 2用户充值")
    private int type;

}
