package com.manager.controller.dataAnalysis;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.AddUser;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataAnalysisService;
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
 * 新增用户
 * @author sieGuang 2021/11/06
 */
@RestController
@Api(tags = "新增用户")
@RequestMapping("/data/addUser")
public class AddUserController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    /**
     * 查询新增用户
     */
    @PreAuthorize("@ss.hasPermi('data:addUser:list')")
    @ApiOperation(value = "查询新增用户")
    @PostMapping("/list")
    public AjaxResult getAddUser(@RequestBody AddUser addUser) {
        addUser.setTid2(SecurityUtils.getUserId());
        return dataAnalysisService.getAddUser(addUser);
    }

    @PreAuthorize("@ss.hasPermi('data:addUser:list')")
    @ApiOperation(value = "新增用户导出")
    @PostMapping("/export")
    public void gameExport(@RequestBody AddUser addUser, HttpServletResponse response) throws IOException {
        addUser.setTid2(SecurityUtils.getUserId());
        List list = (List) dataAnalysisService.getAddUser(addUser).get("data");
        ExcelUtil<AddUser> util = new ExcelUtil(AddUser.class);
        String fileName = "新增用户导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setFileName(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

}
