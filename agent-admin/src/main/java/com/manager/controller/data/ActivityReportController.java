package com.manager.controller.data;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason
 * @date 2021-10-08
 */
@RestController
@Api(tags = "数据分析-活动列表")
@RequestMapping("/data/activity/report")
public class ActivityReportController extends BaseController {

    @Autowired
    private DataService dataService;

    /**
     * 获取活动列表
     */
    @ApiOperation(value = "获取活动列表")
    @GetMapping("/list")
    public AjaxResult list(Coins coins) {
        return dataService.getActivityList(coins);
    }

}
