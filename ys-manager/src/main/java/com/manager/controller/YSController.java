package com.manager.controller;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.system.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/6
 */
@RestController
@RequestMapping("/game/recharge")
@Api(tags = "银商系统管理")
public class YSController extends BaseController {

    @Autowired
    private RechargeService rechargeService;

    /**
     * 获取银商下拉框
     */
    @ApiOperation(value = "获取银商下拉列表")
    @GetMapping("/ysoption")
    public AjaxResult ysOption() {
        return AjaxResult.success(rechargeService.getYsOption());
    }
}
