package com.manager.controller.agent;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysTenantChannelStatistics;
import com.manager.common.core.domain.entity.SysTenantStatistics;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.system.service.SysTenantChannelStatisticsService;
import com.manager.system.service.SysTenantStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @ApiOperation(value = "总代渠道数据")
    @Log(title = "总代渠道数据导出", businessType = BusinessType.EXPORT)
    @PostMapping("/statistics/export")
    public void export(@RequestBody SysTenantStatistics param, HttpServletResponse response) throws IOException {
        SysTenantStatistics sysTenantStatistics = sysTenantStatisticsService.getSysTenantStatistics(param);
        ExcelUtil<SysTenantStatistics> util = new ExcelUtil(SysTenantStatistics.class);
        List<SysTenantStatistics> list = new ArrayList<>();
        list.add(sysTenantStatistics);
        String fileName = "提现top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }


    @PreAuthorize("@ss.hasPermi('system:tenant:statistics:list')")
    @ApiOperation(value = "渠道明细汇总列表")
    @GetMapping("/channelList")
    public AjaxResult channelList(SysTenantChannelStatistics param) {
        this.startPage();
        List<SysTenantChannelStatistics> list = sysTenantChannelStatisticsService.list(param);
        if (list != null && list.get(0) == null) {
            list = new ArrayList<>();
        }
        return AjaxResult.success(getDataTable(list));
    }

    @PreAuthorize("@ss.hasPermi('system:tenant:statistics:list')")
    @ApiOperation(value = "渠道明细汇总导出")
    @Log(title = "渠道明细汇总导出", businessType = BusinessType.EXPORT)
    @PostMapping("/channelList/export")
    public void export(@RequestBody SysTenantChannelStatistics param, HttpServletResponse response) throws IOException {
        List<SysTenantChannelStatistics> list = sysTenantChannelStatisticsService.list(param);
        if (list != null && list.get(0) == null) {
            list = new ArrayList<>();
        }
        ExcelUtil<SysTenantChannelStatistics> util = new ExcelUtil(SysTenantChannelStatistics.class);
        String fileName = "提现top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }


}
