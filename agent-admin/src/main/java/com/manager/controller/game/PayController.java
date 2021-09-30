package com.manager.controller.game;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Pay;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商城页签管理
 * @author sieGuang 2021/09/07
 */
@RestController
@RequestMapping("/game/pay")
@Api(tags = "商场页签管理")
public class PayController extends BaseController {

    @Autowired
    private PayService payService;

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:game:listPay')")
    @ApiOperation(value = "查询客服信息")
    @PostMapping("/listPay")
    public AjaxResult getPayList() {
        return AjaxResult.success("查询成功", payService.getPayList());
    }

    /**
     * 编辑
     * @param pay 需要修改的内容
     */
    @PreAuthorize("@ss.hasPermi('system:game:editPay')")
    @ApiOperation(value = "编辑商场页签")
    @Log(title = "编辑商场页签", businessType = BusinessType.UPDATE)
    @PostMapping("/editPay")
    public AjaxResult editPay(@RequestBody Pay pay) {
        int i = payService.editPay(pay);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
