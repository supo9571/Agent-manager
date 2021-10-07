package com.manager.controller.data;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.core.domain.model.SubGameDataExcel;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.SubGameDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author marvin 2021/10/7
 */
@RestController
@Api(tags = "子游戏实时数据")
@RequestMapping("/data/subgame")
public class SubGameDataController {

    @Autowired
    private SubGameDataService subGameDataService;
    /**
     * 获取子游戏实时数据
     */
    @PreAuthorize("@ss.hasPermi('data:subgame:list')")
    @ApiOperation(value = "查询游戏数据")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SubGameData subGameData) {
        subGameData.setTid(ManagerConfig.getTid());
        return subGameDataService.getSubGameDate(subGameData);
    }

    @PreAuthorize("@ss.hasPermi('data:subgame:list')")
    @ApiOperation(value = "游戏数据导出")
    @PostMapping("/gameExport")
    public void gameExport(@RequestBody SubGameData subGameData, HttpServletResponse response) throws IOException {
        subGameData.setTid(ManagerConfig.getTid());
        List list = (List) subGameDataService.getSubGameDate(subGameData).get("data");
        ExcelUtil<SubGameDataExcel> util = new ExcelUtil(SubGameDataExcel.class);
        String fileName = "游戏数据导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }
    /**
     * 获取子游戏 房间数据
     */
    @PreAuthorize("@ss.hasPermi('data:subgame:list')")
    @ApiOperation(value = "查询房间明细")
    @PostMapping("/table")
    public AjaxResult table(@RequestBody SubGameData subGameData) {
        subGameData.setTid(ManagerConfig.getTid());
        return subGameDataService.getTableDate(subGameData);
    }

    @PreAuthorize("@ss.hasPermi('data:subgame:list')")
    @ApiOperation(value = "房间明细导出")
    @PostMapping("/tableExport")
    public void tableExport(@RequestBody SubGameData subGameData, HttpServletResponse response) throws IOException {
        subGameData.setTid(ManagerConfig.getTid());
        List list = (List) subGameDataService.getTableDate(subGameData).get("data");
        ExcelUtil<SubGameDataExcel> util = new ExcelUtil(SubGameDataExcel.class);
        String fileName = "房间明细导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }
}
