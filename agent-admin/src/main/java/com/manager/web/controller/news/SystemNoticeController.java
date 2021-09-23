package com.manager.web.controller.news;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SystemNotice;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUploadUtils;
import com.manager.system.service.SystemNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 系统公告配置
 * @author sieGuang 2021/09/18
 */
@RestController
@RequestMapping("/news/systemNotice")
@Api(tags = "系统公告配置")
@Slf4j
public class SystemNoticeController extends BaseController {

    @Autowired
    private SystemNoticeService systemNoticeService;

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:addSystemNotice')")
    @ApiOperation(value = "添加系统公告")
    @Log(title = "添加系统公告", businessType = BusinessType.INSERT)
    @PostMapping("/addSystemNotice")
    public AjaxResult addSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNotice.setCreateBy(SecurityUtils.getUsername());
        systemNotice.setTid(ManagerConfig.getTid());
        Integer i = systemNoticeService.addSystemNotice(systemNotice);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:listSystemNotice')")
    @ApiOperation(value = "查询系统公告列表")
    @PostMapping("/listSystemNotice")
    public AjaxResult listSystemNotice(@RequestBody SystemNotice systemNotice) {
        startPage();
        systemNotice.setTid(ManagerConfig.getTid());
        List list = systemNoticeService.listSystemNotice(systemNotice);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:editSystemNotice')")
    @ApiOperation(value = "编辑系统公告")
    @Log(title = "编辑系统公告", businessType = BusinessType.UPDATE)
    @PostMapping("/editSystemNotice")
    public AjaxResult editSystemNotice(@RequestBody SystemNotice systemNotice) {
        systemNotice.setTid(ManagerConfig.getTid());
        systemNotice.setUpdateBy(SecurityUtils.getUsername());
        Integer i = systemNoticeService.editSystemNotice(systemNotice);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:recharge:delSystemNotice')")
    @ApiOperation(value = "删除系统公告")
    @Log(title = "删除系统公告", businessType = BusinessType.DELETE)
    @PostMapping("/delSystemNotice")
    public AjaxResult delSystemNotice(String id) {
        Integer i = systemNoticeService.delSystemNotice(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 文件上传
     */
    @PreAuthorize("@ss.hasPermi('data:recharge:hotUpload')")
    @ApiOperation(value = "文件上传")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/hotUpload")
    public AjaxResult hotUpload(MultipartFile file) {
        JSONObject jsonObject;
        try {
            jsonObject = FileUploadUtils.uploadUnzip(ManagerConfig.getProfile()+"/recharge",file);
        } catch (Exception e) {
            log.error("系统公告上传出错:{}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(jsonObject);
    }

}
