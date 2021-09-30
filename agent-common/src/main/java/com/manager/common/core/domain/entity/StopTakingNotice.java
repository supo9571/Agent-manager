package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 停服公告
 *
 * @author sieGuang 2021/09/27
 */
@Data
public class StopTakingNotice extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("署名")
    private String signature;

    @ApiModelProperty("通知时间")
    private String noticeTime;

}
