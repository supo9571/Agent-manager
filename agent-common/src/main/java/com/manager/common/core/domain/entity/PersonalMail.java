package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 个人邮箱配置
 *
 * @author sieGuang 2021/9/20
 */
@Data
public class PersonalMail extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("收件人")
    private String addressee;

    @ApiModelProperty("收件人类型 1所有玩家 2指定渠道 3指定代理 4新注册玩家")
    private String addresseeType;

    @ApiModelProperty("类型 1文本公告 2图片公告")
    private String type;

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @ApiModelProperty("公告内容")
    private String noticeContent;

    @ApiModelProperty("正文署名")
    private String noticeSignature;

    @ApiModelProperty("发送金币")
    private String sendOutGold;

    @ApiModelProperty("状态 1待发送 2在线 3已下线")
    private String state;

    @ApiModelProperty("发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendOutTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;

}
