package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 跑马灯配置
 *
 * @author sieGuang 2021/9/21
 */
@Data
public class HorseRaceLamp extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("间隔（分钟）")
    private String intervalTitle;

    @ApiModelProperty("状态 1待发送 2在线 3已下线")
    private String state;

    @ApiModelProperty("跑马灯等级")
    private String horseRaceGrade;

    @ApiModelProperty("发起时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("发起时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;

    @ApiModelProperty("播放开始时间(字符串就好)")
    private String playBeginTime;

    @ApiModelProperty("播放结束时间(字符串就好)")
    private String playEndTime;

}
