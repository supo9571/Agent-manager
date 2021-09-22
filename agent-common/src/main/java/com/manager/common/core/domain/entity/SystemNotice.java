package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统公告配置
 * @author sieGuang 2021/9/20
 */
@Data
public class SystemNotice extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("收件人")
    private String addressee;

    @ApiModelProperty("收件人类型 1所有玩家 2指定渠道 3指定代理")
    private String addresseeType;

    @ApiModelProperty("类型 1文本公告 2图片公告")
    private String type;

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @ApiModelProperty("公告内容")
    private String noticeContent;

    @ApiModelProperty("正文署名")
    private String noticeSignature;

    @ApiModelProperty("状态 1待发送 2在线 3已下线")
    private String state;

    @ApiModelProperty("推送设置 1推送 2不推送")
    private String push;

    private String sendOutTime;

    @ApiModelProperty("发送时间类型 1立即发送 2定时发送")
    private String sendOutTimeType;

    @ApiModelProperty("创建时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("创建时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;


}
