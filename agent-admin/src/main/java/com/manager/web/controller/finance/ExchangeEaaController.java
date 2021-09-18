package com.manager.web.controller.finance;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.*;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.system.service.ExchangeEaaService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 提现审批
 * @author sieGuang 2021/09/16
 */
@RestController
@RequestMapping("/finance/exchangeEaa")
@Api(tags = "提现审批")
public class ExchangeEaaController extends BaseController {

    @Autowired
    private ExchangeEaaService exchangeEaaService;

    /**
     * 查询提现审批数据
     */
    @ApiOperation(value = "提现审批查询")
    @PostMapping("/listExchangeEaa")
    public AjaxResult getExchangeEaaList(@RequestBody ExchangeEaa exchangeEaa) {
        return AjaxResult.success("查询成功", exchangeEaaService.getExchangeEaaList(exchangeEaa));
    }

    /**
     * 玩家打码信息查询
     */
    @ApiOperation(value = "玩家打码信息查询")
    @PostMapping("/getAddMosaicPlayerList")
    public AjaxResult getAddMosaicPlayerList(String uid,String tid) {
        return AjaxResult.success("查询成功", exchangeEaaService.getAddMosaicPlayerList(uid,tid));
    }

    @PreAuthorize("@ss.hasPermi('system:finance:exportRechargeOrder')")
    @ApiOperation(value = "提现审批导出")
    @Log(title = "提现审批导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody ExchangeEaa exchangeEaa, HttpServletResponse response) throws IOException {
        startOrder(exchangeEaa.getOrderByColumn(),exchangeEaa.getIsAsc());
        List<ExchangeEaa> list = exchangeEaaService.export(exchangeEaa);

        ExcelUtil util = new ExcelUtil<ExchangeEaa>(ExchangeEaa.class);
        String fileName = "充值查询导出";

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName+".xlsx");
        util.downloadExcel(list, fileName,response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('system:game:editExchange')")
    @ApiOperation(value = "编辑商场页签")
    @Log(title = "编辑商场页签", businessType = BusinessType.UPDATE)
    @PostMapping("/editExchangeEaaList")
    public AjaxResult editExchangeEaaList(@RequestBody ExchangeEaa exchangeEaa) {
        int i = exchangeEaaService.editExchangeEaaList(exchangeEaa);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
