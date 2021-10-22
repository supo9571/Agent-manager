package com.manager.common.core.domain.model.param;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jason
 * @date 2021-10-19
 */
@Data
public class PlayerReportParam extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("渠道号")
    private Integer channelId;

    @ApiModelProperty(value = "玩家id/手机号")
    private String keyword;

    @ApiModelProperty("上级代理ID")
    private Integer agentId;

    @ApiModelProperty("所在游戏 下拉选项")
    private Integer gameId;

    @ApiModelProperty("排序类型：1.投注金额 2.派奖金额 3.盈亏")
    private Integer orderType = 3;

}
