package com.manager.common.core.domain.model.param;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jason
 * @date 2021-10-08
 */
@Data
public class DataAnalysisParam extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tId;

    @ApiModelProperty("渠道号")
    private Integer channelId;

    @ApiModelProperty(value = "uid",hidden = true)
    private Integer uid;

    @ApiModelProperty("全民代理类型：1直属下级排行 2.累计佣金排行")
    private Integer type = 1;

}
