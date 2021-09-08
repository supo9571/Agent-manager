package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客服信息管理
 * @author sieGuang 2021/09/06
 */
@Data
public class Consumer extends BaseEntity {

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("显示地址  1 大厅页面 2 推广页面 3 注册页面")
    private String accordAddr;

    @ApiModelProperty("状态1开启2关闭")
    private String status;

    @ApiModelProperty("打开方式1外部链接2弹框")
    private String openType;

    @ApiModelProperty("live800的网址")
    private String liveUrl;

    @ApiModelProperty("是json字符串 type是类型 1：QQ 2：微信 3：其他 val是值")
    private String info;

}
