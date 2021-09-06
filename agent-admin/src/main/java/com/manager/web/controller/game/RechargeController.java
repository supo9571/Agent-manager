package com.manager.web.controller.game;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.VipRecharge;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/9/6
 */
@RestController
@RequestMapping("/game/recharge")
@Api(tags = "充值信息管理")
public class RechargeController extends BaseController {

    @Autowired
    private RechargeService rechargeService;

    /**
     * 添加 vip充值
     */
    @PreAuthorize("@ss.hasPermi('system:game:addvip')")
    @ApiOperation(value = "添加vip充值")
    @Log(title = "添加vip充值", businessType = BusinessType.INSERT)
    @PostMapping("/addvip")
    public AjaxResult addvip(VipRecharge vipRecharge) {
        vipRecharge.setCreateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.saveVipRecharge(vipRecharge);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询 vip充值列表
     */
    @PreAuthorize("@ss.hasPermi('system:game:viplist')")
    @ApiOperation(value = "查询vip充值列表")
    @PostMapping("/viplist")
    public AjaxResult vipList(VipRecharge vipRecharge) {
        startPage();
        List list = rechargeService.findVipRecharge();
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑 vip充值
     */
    @PreAuthorize("@ss.hasPermi('system:game:editvip')")
    @ApiOperation(value = "编辑vip充值")
    @Log(title = "编辑vip充值", businessType = BusinessType.UPDATE)
    @PostMapping("/editvip")
    public AjaxResult editVip(VipRecharge vipRecharge) {
        vipRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.updateVipRecharge(vipRecharge);
        return i>0?AjaxResult.success():AjaxResult.error();
    }
}
