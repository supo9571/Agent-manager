package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/19
 */
@RestController
@Api(tags = "账变记录")
@RequestMapping("/data/coins")
public class CoinsController {

    @Autowired
    private DataService dataService;

    /**
     * 获取账变记录 列表
     */
    @PreAuthorize("@ss.hasPermi('data:coins:list')")
    @ApiOperation(value = "查询账变列表")
    @GetMapping("/list")
    public AjaxResult list(Coins coins) {
        return dataService.getCoins(coins);
    }
}
