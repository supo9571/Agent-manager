package com.manager.controller.game;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.*;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
    @PreAuthorize("@ss.hasPermi('system:recharge:addvip')")
    @ApiOperation(value = "添加vip充值")
    @Log(title = "添加vip充值", businessType = BusinessType.INSERT)
    @PostMapping("/addvip")
    public AjaxResult addvip(VipRecharge vipRecharge) {
        vipRecharge.setCreateBy(SecurityUtils.getUsername());
        vipRecharge.setTid(ManagerConfig.getTid());
        Integer i = rechargeService.saveVipRecharge(vipRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询 vip充值列表
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:viplist')")
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
    @PreAuthorize("@ss.hasPermi('system:recharge:editvip')")
    @ApiOperation(value = "编辑vip充值")
    @Log(title = "编辑vip充值", businessType = BusinessType.UPDATE)
    @PostMapping("/editvip")
    public AjaxResult editVip(VipRecharge vipRecharge) {
        vipRecharge.setTid(ManagerConfig.getTid());
        vipRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.updateVipRecharge(vipRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 线上充值 商城页签下拉列表
     */
    @ApiOperation(value = "商城页签下拉列表")
    @PostMapping("/option")
    public AjaxResult payOption() {
        return AjaxResult.success(rechargeService.getOnlinePays());
    }

    /**
     * 添加 线上充值
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addonline')")
    @ApiOperation(value = "添加线上充值")
    @Log(title = "添加线上充值", businessType = BusinessType.INSERT)
    @PostMapping("/addonline")
    public AjaxResult addOnline(OnlineRecharge onlineRecharge) {
        onlineRecharge.setTid(ManagerConfig.getTid());
        onlineRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.saveOnlineRecharge(onlineRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询 线上充值 列表
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:onlinelist')")
    @ApiOperation(value = "查询线上充值")
    @PostMapping("/onlinelist")
    public AjaxResult onlineList(OnlineRecharge onlineRecharge) {
        startPage();
        onlineRecharge.setTid(ManagerConfig.getTid());
        List list = rechargeService.findOnlineRecharge(onlineRecharge);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑 线上充值
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editonline')")
    @ApiOperation(value = "编辑线上充值")
    @PostMapping("/editonline")
    public AjaxResult editOnline(OnlineRecharge onlineRecharge) {
        onlineRecharge.setTid(ManagerConfig.getTid());
        onlineRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.updateOnlineRecharge(onlineRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 添加 银行卡充值
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addbank')")
    @ApiOperation(value = "添加银行卡充值")
    @Log(title = "添加银行卡充值", businessType = BusinessType.INSERT)
    @PostMapping("/addbank")
    public AjaxResult addBank(BankRecharge bankRecharge) {
        bankRecharge.setTid(ManagerConfig.getTid());
        bankRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.saveBankRecharge(bankRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询 银行卡充值 列表
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:banklist')")
    @ApiOperation(value = "查询银行卡充值")
    @PostMapping("/banklist")
    public AjaxResult bankList(BankRecharge bankRecharge) {
        startPage();
        bankRecharge.setTid(ManagerConfig.getTid());
        List list = rechargeService.findBankRecharge(bankRecharge);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 修改 银行卡充值 配置
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editbank')")
    @ApiOperation(value = "修改银行卡充值")
    @PostMapping("/editbank")
    public AjaxResult editBank(BankRecharge bankRecharge) {
        bankRecharge.setTid(ManagerConfig.getTid());
        bankRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.updateBankRecharge(bankRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 添加 月卡
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addmonth')")
    @ApiIgnore
//    @ApiOperation(value = "添加月卡配置")
    @Log(title = "添加月卡配置", businessType = BusinessType.INSERT)
    @PostMapping("/addmonth")
    public AjaxResult addMonth(MonthRecharge monthRecharge) {
        monthRecharge.setTid(ManagerConfig.getTid());
        monthRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.saveMonthRecharge(monthRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询 月卡
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:monthlist')")
    @ApiOperation(value = "查询月卡配置")
    @PostMapping("/monthlist")
    public AjaxResult monthList(MonthRecharge monthRecharge) {
        startPage();
        monthRecharge.setTid(ManagerConfig.getTid());
        List list = rechargeService.findMonthRecharge(monthRecharge);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 修改 月卡
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editmonth')")
    @ApiOperation(value = "编辑月卡配置")
    @Log(title = "编辑月卡配置", businessType = BusinessType.UPDATE)
    @PostMapping("/editmonth")
    public AjaxResult editMonth(MonthRecharge monthRecharge) {
        monthRecharge.setTid(ManagerConfig.getTid());
        monthRecharge.setUpdateBy(SecurityUtils.getUsername());
        Integer i = rechargeService.updateMonthRecharge(monthRecharge);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 添加银商信息
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addys')")
    @ApiOperation(value = "添加银商信息")
    @Log(title = "添加银商信息", businessType = BusinessType.INSERT)
    @PostMapping("/addys")
    public AjaxResult addYs(Ysinfo ysinfo) {
        ysinfo.setTid(ManagerConfig.getTid());
        Integer i = rechargeService.saveYsinfo(ysinfo);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 查询 银商信息
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:yslist')")
    @ApiOperation(value = "查询银商列表")
    @PostMapping("/yslist")
    public AjaxResult ysList() {
        return AjaxResult.success(rechargeService.findYsinfo());
    }

    /**
     * 编辑 银商信息
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editys')")
    @Log(title = "编辑银商列表", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "编辑银商列表")
    @PostMapping("/editys")
    public AjaxResult editYs(Ysinfo ysinfo) {
        ysinfo.setTid(ManagerConfig.getTid());
        Integer i = rechargeService.updateYsinfo(ysinfo);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 获取银商下拉框
     */
    @ApiOperation(value = "获取银商下拉列表")
    @GetMapping("/ysoption")
    public AjaxResult ysOption() {
        return AjaxResult.success(rechargeService.getYsOption());
    }

    /**
     * 银商额度充值
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editys')")
    @Log(title = "银商额度充值", businessType = BusinessType.INSERT)
    @ApiOperation(value = "银商额度充值")
    @GetMapping("/ysrecharge")
    public AjaxResult ysRecharge(Integer ysid,Long amount) {
        rechargeService.ysRecharge(ysid,amount);
        return AjaxResult.success();
    }

    /**
     * 银商额度记录
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editys')")
    @ApiOperation(value = "银商额度记录")
    @GetMapping("/ysquota")
    public AjaxResult ysquota(Integer ysid,Integer type,String beginTime,String endTime) {
        startPage();
        List list = rechargeService.ysquota(ysid,type,beginTime,endTime);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 银商报表
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editys')")
    @ApiOperation(value = "银商报表")
    @GetMapping("/ysreport")
    public AjaxResult ysreport(Integer ysid,String ysname,String beginTime,String endTime,Long transferInMin,Long transferInMax,Long transferOutMin,Long transferOutMax) {
        startPage();
        List list = rechargeService.ysreport(ysid,ysname,beginTime,endTime,transferInMin,transferInMax,transferOutMin,transferOutMax);
        return AjaxResult.success(getDataTable(list));
    }
}
