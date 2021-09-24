package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 邮箱记录
 * @author sieGuang 2021/9/20
 */
@Data
public class MailRecord extends BaseEntity {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("日记排序")
    private Integer sort;

    @ApiModelProperty("署名")
    private String signature;

    @ApiModelProperty("收件人")
    private String addressee;

    @ApiModelProperty("邮件标题")
    private String mailTitle;

    @ApiModelProperty("邮件内容")
    private String mailContent;

    @ApiModelProperty("邮件状态 1已读 2未读 3已经领取 4未领取 5待发送 6测回")
    private String mailState;

    @ApiModelProperty("邮件道具(金币)")
    private String mailProp;

}
