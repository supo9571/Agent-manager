package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.ExchangeEaa;
import com.manager.common.core.domain.model.Coins;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        //coins.setTid(ManagerConfig.getTid());
        return dataService.getActivityList(coins);
    }

    @ApiOperation(value = "活动列表导出")
    @Log(title = "活动列表导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody Coins coins, HttpServletResponse response) throws IOException {
        coins.setTid(ManagerConfig.getTid()+"");
        AjaxResult ajaxResult = dataService.getActivityList(coins);
        ExcelUtil util = new ExcelUtil(Map.class);
        String fileName = "活动列表导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

}
