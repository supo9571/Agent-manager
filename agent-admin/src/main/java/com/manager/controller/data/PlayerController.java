package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.PlayWater;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.core.domain.model.UserLock;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.openFegin.DataService;
import com.manager.system.service.BankService;
import com.manager.system.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author marvin 2021/8/19
 */
@RestController
@Api(tags = "玩家列表")
@RequestMapping("/data/player")
public class PlayerController extends BaseController {

    @Autowired
    private DataService dataService;

    @Autowired
    private PlayerService playerService;
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "查询玩家列表")
    @PostMapping("/list")
    public AjaxResult list(PlayUser playUser) {
        return dataService.getPlayers(playUser);
    }

    /**
     * 查询玩家余额
     */
    @PreAuthorize("@ss.hasPermi('data:player:curr')")
    @ApiOperation(value = "查询玩家余额")
    @GetMapping("/curr")
    public AjaxResult curr(Long uid) {
        return dataService.getPlayerCurr(uid);
    }


    /**
     * 修改玩家信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "修改玩家信息")
    @Log(title = "修改玩家信息", businessType = BusinessType.UPDATE)
    @GetMapping("/edit")
    public AjaxResult edit(PlayUser playUser) {
        return dataService.updatePlayer(playUser);
    }

    /**
     * 玩家信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "信息修改详情")
    @GetMapping("/info")
    public AjaxResult info(Long uid) {
        return dataService.getInfo(uid);
    }

    /**
     * 修改 银行卡/支付宝 信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "银行卡/支付宝修改")
    @Log(title = "修改玩家银行卡/支付宝信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editExchange")
    public AjaxResult editBank(@RequestBody UserExchange userExchange) {
        return dataService.updateBank(userExchange);
    }

    /**
     * 充值提现 数据
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "充值提现数据")
    @PostMapping("/recAndexc")
    public AjaxResult recAndexc(Long uid) {
        return dataService.recAndexc(uid);
    }

    /**
     * 充值记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "充值记录")
    @PostMapping("/rechargeInfo")
    public AjaxResult rechargeInfo(@RequestBody PlayUser playUser) {
        return dataService.rechargeInfo(playUser);
    }

    /**
     * 提现记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "提现记录")
    @PostMapping("/exchangeInfo")
    public AjaxResult exchangeInfo(@RequestBody PlayUser playUser) {
        return dataService.exchangeInfo(playUser);
    }

    /**
     * 推广记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "推广记录")
    @PostMapping("/popularize")
    public AjaxResult popularize(String uid) {
        startPage();
        return AjaxResult.success("查询成功",getDataTable(playerService.getPopularizes(uid)));
    }

    /**
     * 基本详情
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "基本详情")
    @PostMapping("/userInfo")
    public AjaxResult userInfo(Long uid) {
        return dataService.userInfo(uid);
    }

    /**
     * 流水日志
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "流水日志")
    @PostMapping("/waterInfo")
    public AjaxResult waterInfo(@RequestBody PlayWater playWater) {
        return dataService.waterInfo(playWater);
    }

    /**
     * 更改设备码
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "更改设备码")
    @Log(title = "更改设备码", businessType = BusinessType.UPDATE)
    @PostMapping("/updateToken")
    public AjaxResult updateToken(Long uid) {
        return dataService.updateToken(uid);
    }

    /**
     * 封号解封
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "封号解封")
    @Log(title = "封号解封", businessType = BusinessType.UPDATE)
    @PostMapping("/lock")
    public AjaxResult lock(@RequestBody UserLock userLock) {
        userLock.setCreateBy(SecurityUtils.getUsername());
        return dataService.lock(userLock);
    }

    /**
     * 封号解封记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "封号解封记录")
    @PostMapping("/lockLog")
    public AjaxResult lockLog(Long uid) {
        return dataService.lockLog(uid);
    }

    @Autowired
    private BankService bankService;

    /**
     * 获取 银行卡列表
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "银行卡列表")
    @GetMapping("/bankList")
    public AjaxResult bankList() {
        return AjaxResult.success(bankService.getBankList());
    }
}
