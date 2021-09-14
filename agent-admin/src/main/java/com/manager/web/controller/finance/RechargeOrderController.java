package com.manager.web.controller.finance;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.RechargeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 充值订单查询
 * @author sieGuang 2021/09/10
 */
@RestController
@RequestMapping("/finance/rechargeOrder")
@Api(tags = "充值订单")
public class RechargeOrderController extends BaseController {

    @Autowired
    private RechargeOrderService rechargeOrderService;

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:game:listRechargeOrder')")
    @ApiOperation(value = "充值订单查询")
    @PostMapping("/listRechargeOrder")
    public AjaxResult getRechargeOrderList(@RequestBody RechargeOrder rechargeOrder) {
        startOrders(rechargeOrder.getOrderByColumns());
        return AjaxResult.success("查询成功", rechargeOrderService.getRechargeOrderList(rechargeOrder));
    }

    /**
     * 手动充值
     * @param rechargeOrder 增加的字段
     */
    @PostMapping("/addRechargeOrder")
    public AjaxResult addRechargeOrder(@RequestBody RechargeOrder rechargeOrder) {
        Integer i = rechargeOrderService.addRechargeOrder(rechargeOrder);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 确认充值、取消充值
     * @param rechargeOrder
     */
    @PostMapping("/editRechargeOrder")
    public AjaxResult editRechargeOrder(@RequestBody RechargeOrder rechargeOrder) {
        Integer i = rechargeOrderService.editRechargeOrder(rechargeOrder);
        return i>0?AjaxResult.success():AjaxResult.error();
    }


}
