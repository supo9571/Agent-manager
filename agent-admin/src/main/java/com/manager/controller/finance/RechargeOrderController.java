package com.manager.controller.finance;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.*;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.ReportService;
import com.manager.system.service.MailRecordService;
import com.manager.system.service.RechargeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 充值订单查询
 *
 * @author sieGuang 2021/09/10
 */
@RestController
@RequestMapping("/finance/rechargeOrder")
@Api(tags = "充值订单")
public class RechargeOrderController extends BaseController {

    @Autowired
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private MailRecordService mailRecordService;


    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:finance:listRechargeOrder')")
    @ApiOperation(value = "充值订单查询")
    @PostMapping("/listRechargeOrder")
    public AjaxResult getRechargeOrderList(@RequestBody RechargeOrder rechargeOrder) {
        return AjaxResult.success("查询成功", rechargeOrderService.getRechargeOrderList(rechargeOrder));
    }

    /**
     * 手动充值
     *
     * @param rechargeOrder 增加的字段
     */
    @PreAuthorize("@ss.hasPermi('system:finance:addRechargeOrder')")
    @ApiOperation(value = "新增手动充值")
    @Log(title = "新增手动充值", businessType = BusinessType.INSERT)
    @PostMapping("/addRechargeOrder")
    public AjaxResult addRechargeOrder(@RequestBody RechargeOrder rechargeOrder) {
        Integer tid = ManagerConfig.getTid();
        // 判断充值的用户是否存在
        if(rechargeOrder != null){
            rechargeOrder.setUpdateBy(SecurityUtils.getUsername());
            // 处理系统赠送多uid的情况
            if("5".equals(rechargeOrder.getRechargeType()) && rechargeOrder.getUids() != null){
                String[] arrayUids = rechargeOrder.getUids().split(",");
                for (String uid : arrayUids) {
                    Integer uidCount = rechargeOrderService.uidIsPresent(Integer.parseInt(uid), tid);
                    if(uidCount <= 0){
                        return error("用户不存在");
                    }
                }
            }else if(rechargeOrder.getUid() != 0){
                Integer uidCount = rechargeOrderService.uidIsPresent(rechargeOrder.getUid(), tid);
                if(uidCount <= 0){
                    return error("用户不存在");
                }
            }
        }

        //支付类型 1VIP充值 2银行卡充值 3月卡充值 4线上支付 5系统赠送/人工充值 6彩金充值
        BigDecimal b = new BigDecimal(10000);//乘以 一万
        Long amount = rechargeOrder.getRechargeAmount().multiply(b).longValue();//充值金额
        Integer reason = 0; // 充值类型
        Long give = 0l;// 赠送金额
        BigDecimal bigGive = new BigDecimal(0);// 赠送金额
        String cmd = "addcoins";
        if ("1".equals(rechargeOrder.getRechargeType())) {// VIP充值
            //加币类型
            Integer rechargeGive = rechargeOrderService.selectRechargeGive(1);
            bigGive = rechargeOrder.getRechargeAmount().multiply(new BigDecimal(rechargeGive)).multiply(new BigDecimal(100));
            give = bigGive.longValue();
            reason = 100070;
        } else if ("2".equals(rechargeOrder.getRechargeType())) {// 银行卡充值
            Integer rechargeGive = rechargeOrderService.selectRechargeGive(2);
            bigGive = rechargeOrder.getRechargeAmount().multiply(new BigDecimal(rechargeGive));
            give = bigGive.longValue();
            reason = 100073;
        } else if ("3".equals(rechargeOrder.getRechargeType())) {// 月卡充值
            if ("金卡".equals(rechargeOrder.getCommodityName())) {
                //获取月卡 到账金额
                Integer monthGive = rechargeOrderService.selectJinMonthGive();
                amount = new BigDecimal(monthGive).multiply(b).longValue();
                reason = 100071;
            }
            if ("银卡".equals(rechargeOrder.getCommodityName())) {
                Integer monthGive = rechargeOrderService.selectYinMonthGive();
                amount = new BigDecimal(monthGive).multiply(b).longValue();
                reason = 100072;
            }
        } else if ("5".equals(rechargeOrder.getRechargeType())) {//5系统赠送/人工充值
            if ("2".equals(rechargeOrder.getOperateType()) ||
                    "4".equals(rechargeOrder.getOperateType())) {
                cmd = "reducecoins";
            }

            /*Integer rechargeGive = rechargeOrderService.selectRechargeGive(2);//赠送比例
            bigGive = rechargeOrder.getRechargeAmount().multiply(new BigDecimal(rechargeGive));
            give = bigGive.longValue();*/
            reason = 100075;
        }
        //请求 加金币
        BigDecimal currBig = new BigDecimal(0);//余额
        Long curr = 0l;
        Integer i = 0;
        AjaxResult ajaxResult = new AjaxResult();
        // 处理系统赠送多uid的情况
        if ("5".equals(rechargeOrder.getRechargeType()) && rechargeOrder.getUids() != null) {
            String[] arrayUid = rechargeOrder.getUids().split(",");
            for (String uid : arrayUid) {
                ajaxResult = reportService.editCoinsGm(cmd,amount,Integer.parseInt(uid)
                        ,Integer.parseInt(rechargeOrder.getOperateAccount()),reason);

                if ("200".equals(String.valueOf(ajaxResult.get("code")))) {
                    curr = Long.valueOf(String.valueOf(ajaxResult.get("data")));
                    currBig = new BigDecimal(curr).divide(b);
                } else {
                    return error("请求游戏服失败");
                }
                rechargeOrder.setExCoins(bigGive.divide(b));
                rechargeOrder.setAfterOrderMoney(currBig);
                rechargeOrder.setBeforeOrderMoney(currBig.subtract(rechargeOrder.getRechargeAmount()).subtract(rechargeOrder.getExCoins()));
                rechargeOrder.setUid(Integer.parseInt(uid));

                i = rechargeOrderService.addRechargeOrder(rechargeOrder);

                // 发送邮件
                sendOutMail(tid, amount, give, uid);
            }
        } else {
            ajaxResult = reportService.editCoins(amount,give,rechargeOrder.getUid(),reason,curr,0);

            if ("200".equals(String.valueOf(ajaxResult.get("code")))) {
                curr = Long.valueOf(String.valueOf(ajaxResult.get("data")));
                currBig = new BigDecimal(curr).divide(b);
            } else {
                return error("请求游戏服失败");
            }
            rechargeOrder.setExCoins(bigGive.divide(b));
            rechargeOrder.setAfterOrderMoney(currBig);
            rechargeOrder.setBeforeOrderMoney(currBig.subtract(rechargeOrder.getRechargeAmount()).subtract(rechargeOrder.getExCoins()));
            i = rechargeOrderService.addRechargeOrder(rechargeOrder);

            // 发送邮件
            sendOutMail(tid, amount, give, String.valueOf(rechargeOrder.getUid()));
        }
        return i > 0 ? success() : error("充值成功，添加记录失败");
    }

    /**
     * 发送邮件
     * @param tid 平台id
     * @param amount 金额
     * @param give 赠送金额
     * @param uid 用户
     */
    private void sendOutMail(Integer tid, Long amount, Long give, String uid) {
        MailRecord mailRecord = new MailRecord();
        mailRecord.setTid(tid);
        mailRecord.setAddressee(uid);
        mailRecord.setMailTitle("充值成功！");
        mailRecord.setMailContent("恭喜您充值：" + amount + "成功。 另外系统赠送"+ give);

        mailRecordService.sendOutMail(mailRecord);
    }

    /**
     * 确认充值、取消充值
     *
     * @param rechargeOrder
     */
    @PreAuthorize("@ss.hasPermi('system:finance:editRechargeOrder')")
    @ApiOperation(value = "银行卡充值页签-确认充值、取消充值")
    @Log(title = "银行卡充值页签-确认充值、取消充值", businessType = BusinessType.UPDATE)
    @PostMapping("/editRechargeOrder")
    public AjaxResult editRechargeOrder(@RequestBody RechargeOrder rechargeOrder) {
        rechargeOrder.setUpdateBy(SecurityUtils.getUsername());
        if ("1".equals(rechargeOrder.getBankCardRechargeType())) {// 确认充值
            BigDecimal b = new BigDecimal(10000);//乘以 一万
            Long amount = rechargeOrder.getRechargeAmount().multiply(b).longValue();//充值金额
            Integer rechargeGive = rechargeOrderService.selectRechargeGive(2);//赠送比例
            BigDecimal bigGive = rechargeOrder.getRechargeAmount().multiply(new BigDecimal(rechargeGive));
            Long give = bigGive.longValue();
            //请求 加金币
            BigDecimal currBig = new BigDecimal(0);//余额
            Long curr = 0l;
            AjaxResult ajaxResult = reportService.editCoins(amount,give,rechargeOrder.getUid(),100073,0l,0);
            if ("200".equals(String.valueOf(ajaxResult.get("code")))) {
                curr = Long.valueOf(String.valueOf(ajaxResult.get("data")));
                currBig = new BigDecimal(curr).divide(b);
            } else {
                return error("请求游戏服失败");
            }
            rechargeOrder.setExCoins(bigGive.divide(b));
            rechargeOrder.setAfterOrderMoney(currBig);
            rechargeOrder.setBeforeOrderMoney(currBig.subtract(rechargeOrder.getRechargeAmount()).subtract(rechargeOrder.getExCoins()));
            rechargeOrder.setPaymentStatus("1");
            Integer i = rechargeOrderService.editRechargeOrder(rechargeOrder);

            // 发送邮件
            sendOutMail(ManagerConfig.getTid(), amount, give, String.valueOf(rechargeOrder.getUid()));
            return i > 0 ? AjaxResult.success() : AjaxResult.error("充值成功，修改记录失败");
        }
        rechargeOrder.setPaymentStatus("3");
        Integer i = rechargeOrderService.editRechargeOrder(rechargeOrder);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 获取月卡金额
     *
     * @param monthlyCardType 1金卡 2银卡
     */
    @PreAuthorize("@ss.hasPermi('system:finance:getRechargeAmount')")
    @ApiOperation(value = "获取月卡金额")
    @PostMapping("/getRechargeAmount")
    public AjaxResult getRechargeAmount(String monthlyCardType) {
        return AjaxResult.success("查询成功", rechargeOrderService.getRechargeAmount(monthlyCardType));
    }

    @PreAuthorize("@ss.hasPermi('system:finance:exportRechargeOrder')")
    @ApiOperation(value = "导出充值")
    @Log(title = "导出充值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody RechargeOrder rechargeOrder, HttpServletResponse response) throws IOException {
        startOrder(rechargeOrder.getOrderByColumn(), rechargeOrder.getIsAsc());
        List<RechargeOrder> list = rechargeOrderService.export(rechargeOrder);

        List<RechargeOrderExcel> rechargeOrderExcelList = new ArrayList<RechargeOrderExcel>();
        RechargeOrderExcel rechargeOrderExcel;

        List<VipRechargeExcel> vipRechargeExcelList = new ArrayList<VipRechargeExcel>();
        VipRechargeExcel vipRechargeExcel;

        List<BankCardRechargeExcel> bankCardRechargeExcelList = new ArrayList<BankCardRechargeExcel>();
        BankCardRechargeExcel bankCardRechargeExcel;

        List<MonthlyCardRechargeExcel> monthlyCardRechargeExcelList = new ArrayList<MonthlyCardRechargeExcel>();
        MonthlyCardRechargeExcel monthlyCardRechargeExcel;

        List<SendRechargeExcel> sendRechargeExcelList = new ArrayList<SendRechargeExcel>();
        SendRechargeExcel sendRechargeExcel;

        for (RechargeOrder order : list) {
            // 处理导出数据  1充值订单查询 2VIP充值 3银行卡充值 4月卡充值 5系统赠送
            if ("1".equals(rechargeOrder.getExcelType())) {

                rechargeOrderExcel = new RechargeOrderExcel();
                BeanUtils.copyProperties(order, rechargeOrderExcel);
                rechargeOrderExcelList.add(rechargeOrderExcel);
            } else if ("2".equals(rechargeOrder.getExcelType())) {

                vipRechargeExcel = new VipRechargeExcel();
                BeanUtils.copyProperties(order, vipRechargeExcel);
                vipRechargeExcelList.add(vipRechargeExcel);
            } else if ("3".equals(rechargeOrder.getExcelType())) {

                bankCardRechargeExcel = new BankCardRechargeExcel();
                BeanUtils.copyProperties(order, bankCardRechargeExcel);
                bankCardRechargeExcelList.add(bankCardRechargeExcel);
            } else if ("4".equals(rechargeOrder.getExcelType())) {

                monthlyCardRechargeExcel = new MonthlyCardRechargeExcel();
                BeanUtils.copyProperties(order, monthlyCardRechargeExcel);
                monthlyCardRechargeExcelList.add(monthlyCardRechargeExcel);
            } else if ("5".equals(rechargeOrder.getExcelType())) {

                sendRechargeExcel = new SendRechargeExcel();
                BeanUtils.copyProperties(order, sendRechargeExcel);
                sendRechargeExcelList.add(sendRechargeExcel);
            }
        }

        // 处理导出模板  1充值订单查询 2VIP充值 3银行卡充值 4月卡充值 5系统赠送
        ExcelUtil util;
        String fileName;
        if ("1".equals(rechargeOrder.getExcelType())) {
            util = new ExcelUtil<RechargeOrderExcel>(RechargeOrderExcel.class);
            fileName = "充值查询导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(rechargeOrderExcelList, fileName, response.getOutputStream());
        } else if ("2".equals(rechargeOrder.getExcelType())) {
            util = new ExcelUtil<VipRechargeExcel>(VipRechargeExcel.class);
            fileName = "VIP充值导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(vipRechargeExcelList, fileName, response.getOutputStream());
        } else if ("3".equals(rechargeOrder.getExcelType())) {
            util = new ExcelUtil<BankCardRechargeExcel>(BankCardRechargeExcel.class);
            fileName = "银行卡充值导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(bankCardRechargeExcelList, fileName, response.getOutputStream());
        } else if ("4".equals(rechargeOrder.getExcelType())) {
            util = new ExcelUtil<MonthlyCardRechargeExcel>(MonthlyCardRechargeExcel.class);
            fileName = "月卡充值充值导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(monthlyCardRechargeExcelList, fileName, response.getOutputStream());
        } else if ("5".equals(rechargeOrder.getExcelType())) {
            util = new ExcelUtil<SendRechargeExcel>(SendRechargeExcel.class);
            fileName = "系统赠送导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(sendRechargeExcelList, fileName, response.getOutputStream());
        } else {
            util = new ExcelUtil<RechargeOrderExcel>(RechargeOrderExcel.class);
            fileName = "充值查询导出";

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
            util.downloadExcel(rechargeOrderExcelList, fileName, response.getOutputStream());
        }
    }
}
