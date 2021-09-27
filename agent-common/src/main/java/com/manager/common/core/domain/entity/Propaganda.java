package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 宣传活动配置
 * @author sieGuang 2021/09/25
 */
@Data
public class Propaganda extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("排序")
    private int sort;

    @ApiModelProperty("收件人")
    private String addressee;

    @ApiModelProperty("收件人类型 1所有玩家 2指定渠道 3指定代理")
    private String addresseeType;

    @ApiModelProperty("类型 1文本公告 2图片公告")
    private String type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("署名")
    private String signature;

    @ApiModelProperty("状态 1待上线 2生效中 3已下线")
    private String state;

    @ApiModelProperty("创建时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("创建时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;


}
