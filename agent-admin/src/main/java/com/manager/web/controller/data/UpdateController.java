package com.manager.web.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUploadUtils;
import com.manager.common.utils.file.MimeTypeUtils;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author marvin 2021/8/30
 */
@RestController
@Api(tags = "包更新管理")
@RequestMapping("/data")
@Slf4j
public class UpdateController extends BaseController {

    @Autowired
    private DataService dataService;

    /**
     * 新增 整包更新
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:add')")
    @ApiOperation(value = "新增整包更新")
    @Log(title = "整包更新", businessType = BusinessType.INSERT)
    @PostMapping("/allupdate/add")
    public AjaxResult addAllUpdate(@RequestBody Allupdate allupdate) {
        return dataService.addAllUpdate(allupdate);
    }

    /**
     * 整包更新 文件上传
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:upload')")
    @ApiOperation(value = "整包更新文件上传")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/allupdate/upload")
    public AjaxResult upload(MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        String url = "";
        try {
            url = FileUploadUtils.upload(ManagerConfig.getProfile(),file, MimeTypeUtils.APK);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        jsonObject.put("apkUpdateUrl", url);
        jsonObject.put("size", file.getSize() / 1024);
        return AjaxResult.success(jsonObject);
    }

    /**
     * 整包更新 查询
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:list')")
    @ApiOperation(value = "整包更新列表查询")
    @GetMapping("/allupdate/list")
    public AjaxResult list(Allupdate allupdate) {
        return dataService.findAllUpdate(allupdate);
    }

    /**
     * 整包更新 历史查询
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:list')")
    @ApiOperation(value = "整包更新历史查询")
    @GetMapping("/allupdate/history")
    public AjaxResult history(Integer tid) {
        return dataService.findAllUpdateHistory(tid);
    }

    /**
     * 整包更新 编辑
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:edit')")
    @ApiOperation(value = "整包更新编辑")
    @Log(title = "整包更新", businessType = BusinessType.UPDATE)
    @PostMapping("/allupdate/edit")
    public AjaxResult edit(@RequestBody Allupdate allupdate) {
        return dataService.editAllUpdateHistory(allupdate);
    }

    /**
     * 整包更新 删除
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:del')")
    @ApiOperation(value = "整包更新删除")
    @Log(title = "整包更新", businessType = BusinessType.DELETE)
    @PostMapping("/allupdate/del")
    public AjaxResult del(Integer id) {
        return dataService.deleteAllupdate(id);
    }

    /**
     * 查询 热更新
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:list')")
    @ApiOperation(value = "查询热更新")
    @GetMapping("/hotupdate/list")
    public AjaxResult hotUpdateList(Hotupdate hotupdate) {
        return dataService.findHotupdate(hotupdate);
    }

    /**
     * 根据id查询 热更新
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:list')")
    @ApiOperation(value = "热更新详情")
    @GetMapping("/hotupdate/find")
    public AjaxResult findhotUpdate(Integer id) {
        return dataService.findhotUpdateById(id);
    }

    /**
     * 添加 热更新
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:add')")
    @Log(title = "热更新", businessType = BusinessType.INSERT)
    @ApiOperation(value = "添加热更新")
    @PostMapping("/hotupdate/add")
    public AjaxResult addHotUpdate(@RequestBody Hotupdate hotUpdate) {
        return dataService.addHotUpdate(hotUpdate);
    }

    /**
     * 修改 热更新
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:edit')")
    @Log(title = "热更新", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改热更新")
    @PostMapping("/hotupdate/edit")
    public AjaxResult editHotUpdate(@RequestBody Hotupdate hotUpdate) {
        return dataService.editHotUpdate(hotUpdate);
    }

    /**
     * 热更新 删除
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:del')")
    @Log(title = "热更新", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除热更新")
    @GetMapping("/hotupdate/del")
    public AjaxResult delHotupdate(Integer id) {
        return dataService.delHotupdate(id);
    }

    /**
     * 热更新 文件上传
     */
    @PreAuthorize("@ss.hasPermi('data:hotupdate:upload')")
    @ApiOperation(value = "热更新文件上传")
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/hotupdate/upload")
    public AjaxResult hotUpload(MultipartFile file) {
        JSONObject jsonObject;
        try {
            jsonObject = FileUploadUtils.uploadUnzip(ManagerConfig.getProfile()+"/hotpackage",file);
        } catch (Exception e) {
            log.error("热更新文件上传出错:{}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(jsonObject);
    }
}
