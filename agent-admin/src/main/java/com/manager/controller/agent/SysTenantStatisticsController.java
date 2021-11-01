package com.manager.controller.agent;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysTenantChannelStatistics;
import com.manager.common.core.domain.entity.SysTenantStatistics;
import com.manager.system.service.SysTenantChannelStatisticsService;
import com.manager.system.service.SysTenantStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台/总代/渠道汇总数据
 *
 * @author jason
 * @date 2021-09-28
 */
@Api(tags = "平台-总代-渠道汇总数据")
@RestController
@RequestMapping(value = "/sys/tenant/statistics")
public class SysTenantStatisticsController extends BaseController {

    @Resource
    private SysTenantStatisticsService sysTenantStatisticsService;
    @Resource
    private SysTenantChannelStatisticsService sysTenantChannelStatisticsService;

    @PreAuthorize("@ss.hasPermi('system:tenant:statistics:list')")
    @ApiOperation(value = "总代渠道数据(单条)")
    @GetMapping("/get")
    public AjaxResult get(SysTenantStatistics param) {
        SysTenantStatistics sysTenantStatistics = sysTenantStatisticsService.getSysTenantStatistics(param);
        return AjaxResult.success(sysTenantStatistics);
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:statistics:list')")
    @ApiOperation(value = "渠道明细汇总列表")
    @GetMapping("/channelList")
    public AjaxResult channelList(SysTenantChannelStatistics param) {
        this.startPage();
        List<SysTenantChannelStatistics> list = sysTenantChannelStatisticsService.list(param);
        if (list.get(0) == null) {
            list = new ArrayList<>();
        }
        return AjaxResult.success(getDataTable(list));
    }


}
