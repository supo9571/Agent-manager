package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/9/7
 * 活动配置 config_activity
 */
@Data
public class Activity extends BaseEntity {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("活动开始时间")
    private String activityBegin;

    @ApiModelProperty("活动结束时间")
    private String activityEnd;

    @ApiModelProperty("活动类型 113114充值红包 109每日首充 3首充返利 122流水返利 115全民推广 112摇钱树 111救济金")
    private String activityType;

    @ApiModelProperty("活动排序")
    private Integer sort;

    @ApiModelProperty("活动状态 1未开始 2进行中 3已结束")
    private Integer status;

    @ApiModelProperty("活动可参加渠道")
    private String channelList;

    @ApiModelProperty("活动可参加vip等级")
    private String vipList;

    @ApiModelProperty("最低充值金额")
    private String min;

    @ApiModelProperty("活动奖励明细")
    private String activityInfo;
}
