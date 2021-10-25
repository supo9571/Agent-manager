package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.param.PlayerReportParam;
import com.manager.common.core.domain.model.vo.*;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用于数据分析相关接口
 *
 * @author jason
 * @date 2021-10-08
 */
@RestController
@Api(tags = "数据分析")
@RequestMapping("/data/analysis/report")
public class DataAnalysisController extends BaseController {

    @Resource
    private DataService dataService;

    @PreAuthorize("@ss.hasPermi('data:analysis:withdraw:list')")
    @ApiOperation(value = "提现top100")
    @PostMapping("/withdraw/top/List")
    public AjaxResult list(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.withdrawTopList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:withdraw:list')")
    @ApiOperation(value = "提现top100导出")
    @Log(title = "提现top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.withdrawTopList(param);
        ExcelUtil<DataAnalysisVO> util = new ExcelUtil(DataAnalysisVO.class);
        String fileName = "提现top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:water:list')")
    @ApiOperation(value = "流水top100")
    @PostMapping("/water/top/List")
    public AjaxResult getDataWaterTopList(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.getDataWaterTopList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:water:list')")
    @ApiOperation(value = "流水top100导出")
    @Log(title = "流水top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/water/top/export")
    public void dataWaterTopExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.getDataWaterTopList(param);
        ExcelUtil<DataWaterTopVO> util = new ExcelUtil(DataWaterTopVO.class);
        String fileName = "流水top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:recharge:list')")
    @ApiOperation(value = "充值top100")
    @PostMapping("/recharge/top/List")
    public AjaxResult getRechargeTopList(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.getRechargeTopList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:recharge:list')")
    @ApiOperation(value = "充值top100导出")
    @Log(title = "充值top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/recharge/top/export")
    public void getRechargeTopListExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.getRechargeTopList(param);
        ExcelUtil<RechargeTopVO> util = new ExcelUtil(RechargeTopVO.class);
        String fileName = "充值top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }


    @PreAuthorize("@ss.hasPermi('data:analysis:earnings:list')")
    @ApiOperation(value = "净盈利top100")
    @PostMapping("/earnings/top/List")
    public AjaxResult getEarningsTopList(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.getEarningsTopList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:earnings:list')")
    @ApiOperation(value = "净盈利top100导出")
    @Log(title = "净盈利top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/earnings/top/export")
    public void getEarningsTopListExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.getEarningsTopList(param);
        ExcelUtil<EarningsTopVO> util = new ExcelUtil(EarningsTopVO.class);
        String fileName = "净盈利top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:agent:list')")
    @ApiOperation(value = "全民代理top100")
    @PostMapping("/agent/top/List")
    public AjaxResult getAgentTopList(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.getAgentTopList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:agent:list')")
    @ApiOperation(value = "全民代理top100导出")
    @Log(title = "全民代理top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/agent/top/export")
    public void getAgentTopListExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.getAgentTopList(param);
        ExcelUtil<AgentTopVO> util = new ExcelUtil(AgentTopVO.class);
        String fileName = "全民代理top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:pay:list')")
    @ApiOperation(value = "付费习惯")
    @PostMapping("/pay/top/List")
    public AjaxResult getPayInfoList(@RequestBody DataAnalysisParam param) {
        param.setTId(ManagerConfig.getTid());
        return dataService.getPayInfoList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:pay:list')")
    @ApiOperation(value = "付费习惯导出")
    @Log(title = "付费习惯导出", businessType = BusinessType.EXPORT)
    @PostMapping("/pay/top/export")
    public void getPayInfoListExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        param.setTId(ManagerConfig.getTid());
        AjaxResult ajaxResult = dataService.getPayInfoList(param);
        ExcelUtil<PayInfoVO> util = new ExcelUtil(PayInfoVO.class);
        String fileName = "付费习惯导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表")
    @PostMapping("/player/List")
    public AjaxResult getPlayerReportList(@RequestBody PlayerReportParam param) {
        return dataService.getPlayerReportList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表导出")
    @Log(title = "玩家报表导出", businessType = BusinessType.EXPORT)
    @PostMapping("/player/export")
    public void getPlayerReportListExport(@RequestBody PlayerReportParam param, HttpServletResponse response) throws IOException {
        AjaxResult ajaxResult = dataService.getPlayerReportExport(param);
        ExcelUtil<PlayerReportVO> util = new ExcelUtil(PlayerReportVO.class);
        String fileName = "玩家报表导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表-游戏列表")
    @PostMapping("/player/game/List")
    public AjaxResult getPlayerGameReportList(@RequestBody PlayerReportParam param) {
        return dataService.getPlayerGameReportList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表-游戏导出")
    @Log(title = "玩家报表-游戏导出", businessType = BusinessType.EXPORT)
    @PostMapping("/player/game/export")
    public void getPlayerGameReportListExport(@RequestBody PlayerReportParam param, HttpServletResponse response) throws IOException {
        AjaxResult ajaxResult = dataService.getPlayerGameReportList(param);
        ExcelUtil<PlayerGameReportVO> util = new ExcelUtil(PlayerGameReportVO.class);
        String fileName = "玩家报表-游戏导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表-日期明细")
    @PostMapping("/player/day/List")
    public AjaxResult getPlayerDayReportList(@RequestBody PlayerReportParam param) {
        return dataService.getPlayerDayReportList(param);
    }

    @PreAuthorize("@ss.hasPermi('data:analysis:player:list')")
    @ApiOperation(value = "玩家报表-日期明细导出")
    @Log(title = "玩家报表-日期明细导出", businessType = BusinessType.EXPORT)
    @PostMapping("/player/game/day/export")
    public void getPlayerDayReportListExport(@RequestBody PlayerReportParam param, HttpServletResponse response) throws IOException {
        AjaxResult ajaxResult = dataService.getPlayerDayReportList(param);
        ExcelUtil<PlayerDayReportVO> util = new ExcelUtil(PlayerDayReportVO.class);
        String fileName = "日期明细导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

}
