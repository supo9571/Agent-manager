package com.manager.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.annotation.Excel;
import com.manager.common.annotation.Excel.ColumnType;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现审批
 * @author sieGuang 2021/09/16
 */
@Data
public class ExchangeEaa extends BaseEntity {

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty("玩家姓名")
    private String withdrawName;
    @ApiModelProperty("提现用户")
    private String withdrawAccount;

    @Excel(name = "订单号", cellType = ColumnType.STRING)
    @ApiModelProperty("提现订单号")
    private String withdrawOrderNumber;
    @Excel(name = "第三方订单号", cellType = ColumnType.STRING)
    @ApiModelProperty("第三方订单号")
    private String thirdPartyOrderNumber;

    @Excel(name = "玩家id", cellType = ColumnType.STRING)
    @ApiModelProperty("玩家id")
    private Integer uid;
    @Excel(name = "玩家名称")
    @ApiModelProperty("玩家名称")
    private String uname;

    @Excel(name = "所属渠道")
    @ApiModelProperty("所属渠道")
    private String channel;

    @Excel(name = "提现金额")
    @ApiModelProperty("提现金额")
    private BigDecimal withdrawMoney;
    @Excel(name = "需打款金额")
    @ApiModelProperty("需转账金额")
    private BigDecimal transferAmount;
    @Excel(name = "手续费")
    @ApiModelProperty("手续费")
    private BigDecimal poundage;

    @Excel(name = "提现次数")
    @ApiModelProperty("提现次数")
    private String withdrawNumber;

    @Excel(name = "历史提现金额")
    @ApiModelProperty("历史提现金额")
    private BigDecimal historyWithdrawMoney;
    @Excel(name = "累计流水")
    @ApiModelProperty("累计流水")
    private BigDecimal accumulateWater;
    @Excel(name = "累计充值")
    @ApiModelProperty("累计充值")
    private BigDecimal accumulateRecharge;
    @Excel(name = "累计赠送")
    @ApiModelProperty("累计赠送")
    private BigDecimal accumulateExcoins;
    @Excel(name = "充值赠送")
    @ApiModelProperty("充值赠送")
    private BigDecimal rechargeExcoins;

    @Excel(name = "活动奖励(非推广)")
    @ApiModelProperty("活动奖励(非推广)")
    private BigDecimal activityRewards;
    @Excel(name = "线下赠送")
    @ApiModelProperty("线下赠送")
    private BigDecimal offLineExcoins;

    @Excel(name = "注册IP")
    @ApiModelProperty("注册IP")
    private String registerIp;
    @Excel(name = "提现IP")
    @ApiModelProperty("提现IP")
    private String withdrawIp;

    @Excel(name = "发起时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发起时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Excel(name = "提现类型",readConverterExp = "0=支付宝,1=银行卡")
    @ApiModelProperty("提现类型 0支付宝 1银行卡")
    private String withdrawType;
    @Excel(name = "打款方式")
    @ApiModelProperty("打款方式")
    private String transferType;

    @Excel(name = "审核状态")
    @ApiModelProperty("审核状态  1待审批 2待打款 3已打款 4打款中  5打款失败 6已没收 7已退款")
    private String exaaStatus;

    @Excel(name = "备注")
    @ApiModelProperty("备注")
    private String remark;

    @Excel(name = "操作人")
    @ApiModelProperty("操作人")
    private String updateBy;

    @ApiModelProperty("发起时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;
    @ApiModelProperty("发起时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;
}
