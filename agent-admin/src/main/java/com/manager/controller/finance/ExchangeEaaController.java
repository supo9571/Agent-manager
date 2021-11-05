package com.manager.controller.finance;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.ExchangeEaa;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.ReportService;
import com.manager.system.service.ExchangeEaaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * 提现审批
 *
 * @author sieGuang 2021/09/16
 */
@RestController
@RequestMapping("/finance/exchangeEaa")
@Api(tags = "提现审批")
public class ExchangeEaaController extends BaseController {

    @Autowired
    private ExchangeEaaService exchangeEaaService;

    @Autowired
    private ReportService reportService;

    /**
     * 查询提现审批数据
     */
    @ApiOperation(value = "提现审批查询")
    @PreAuthorize("@ss.hasPermi('system:finance:exchangeEaa:list')")
    @PostMapping("/listExchangeEaa")
    public AjaxResult getExchangeEaaList(@RequestBody ExchangeEaa exchangeEaa) {
        return AjaxResult.success("查询成功", exchangeEaaService.getExchangeEaaList(exchangeEaa));
    }

    /**
     * 玩家打码信息查询
     */
    @ApiOperation(value = "玩家打码信息查询")
    @PreAuthorize("@ss.hasPermi('system:finance:exchangeEaa:list')")
    @PostMapping("/getAddMosaicPlayerList")
    public AjaxResult getAddMosaicPlayerList(int uid) {
        return AjaxResult.success("查询成功", exchangeEaaService.getAddMosaicPlayerList(uid));
    }

    @PreAuthorize("@ss.hasPermi('system:finance:exportRechargeOrder')")
    @ApiOperation(value = "提现审批导出")
    @Log(title = "提现审批导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody ExchangeEaa exchangeEaa, HttpServletResponse response) throws IOException {
        startOrder(exchangeEaa.getOrderByColumn(), exchangeEaa.getIsAsc());
        List<ExchangeEaa> list = exchangeEaaService.export(exchangeEaa);

        ExcelUtil util = new ExcelUtil<ExchangeEaa>(ExchangeEaa.class);
        String fileName = "充值查询导出";

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('system:game:editExchange')")
    @ApiOperation(value = "编辑提现审批")
    @Log(title = "编辑提现审批", businessType = BusinessType.UPDATE)
    @PostMapping("/editExchangeEaaList")
    public AjaxResult editExchangeEaaList(@RequestBody ExchangeEaa exchangeEaa) {
        if(exchangeEaa != null && exchangeEaa.getUid() != null){
            exchangeEaa.setUpdateBy(SecurityUtils.getUsername());
            // 7已退款
            if("7".equals(exchangeEaa.getExaaStatus())){
                BigDecimal withdrawMoney = exchangeEaa.getWithdrawMoney();
                if(withdrawMoney != null){
                    withdrawMoney = withdrawMoney.multiply(new BigDecimal("10000"));
                }else{
                    withdrawMoney = new BigDecimal("0");
                }

                // 驳回提现请求用户返金币
                System.out.println(Integer.valueOf(withdrawMoney.intValue()));
                AjaxResult ajaxResult = reportService.returnBack(
                        exchangeEaa.getUid(),Integer.valueOf(withdrawMoney.intValue()));

                // 发邮件告诉提现失败
                if ("200".equals(String.valueOf(ajaxResult.get("code")))) {
                    AjaxResult ajaxResult2 = reportService.sendEmail(2,exchangeEaa.getUid().toString());
                    if(!("200".equals(String.valueOf(ajaxResult2.get("code"))))){
                        error("请求游戏服失败");
                    }
                } else {
                    return error("请求游戏服失败");
                }
            }
            // 状态改为打款中（财务打款直接改为已打款；第三方代付需要收到回调改为已打款/打款失败）
            if("4".equals(exchangeEaa.getExaaStatus())){
                // 财务打款
                if("1".equals(exchangeEaa.getTransferType())){
                    exchangeEaa.setExaaStatus("3");

                    // 打款成功发邮件告知玩家
                    AjaxResult ajaxResult = reportService.sendEmail(2,exchangeEaa.getUid().toString());
                    if(!("200".equals(String.valueOf(ajaxResult.get("code"))))){
                        error("请求游戏服失败");
                    }
                }

                // 只有打款中状态下===第三方回调返回打款失败，才改为打款失败 (等支持模块实现在做这个功能)
            }
        }

        int i = exchangeEaaService.editExchangeEaaList(exchangeEaa);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 获取打款方式下拉框
     */
    @ApiOperation(value = "获取银商下拉列表")
    @GetMapping("/getTransferMode")
    public AjaxResult getTransferMode() {
        return AjaxResult.success(exchangeEaaService.getTransferMode());
    }

}
