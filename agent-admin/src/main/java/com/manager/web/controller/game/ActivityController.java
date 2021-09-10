package com.manager.web.controller.game;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Activity;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/9/7
 */
@RestController
@RequestMapping("/game/activity")
@Api(tags = "特殊活动配置")
public class ActivityController extends BaseController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ManagerConfig managerConfig;
    /**
     * 添加 活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @ApiOperation(value = "添加活动")
    @Log(title = "添加活动", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addActivity(Activity activity) {
        //判断活动时间是否重叠
        if(activityService.checkActivityTime(activity)>0) return AjaxResult.error("已有相同的活动");
        activity.setUpdateBy(SecurityUtils.getUsername());
        Integer i = activityService.saveActivity(activity);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询 活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @ApiOperation(value = "查询活动")
    @PostMapping("/list")
    public AjaxResult list(Activity activity) {
        startPage();
        List list = activityService.findActivity(activity);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑 活动
     */
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @ApiOperation(value = "编辑活动")
    @PostMapping("/edit")
    public AjaxResult edit(Activity activity) {
        //判断活动时间是否重叠
        if(activityService.checkActivityTime(activity)>0) return AjaxResult.error("已有相同的活动");
        activity.setUpdateBy(SecurityUtils.getUsername());
        Integer i = activityService.updateActivity(activity);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 发送活动到游戏服
     */
    @PreAuthorize("@ss.hasPermi('system:activity:send')")
    @ApiOperation(value = "发送活动配置")
//    @Log(title = "发送活动配置", businessType = BusinessType.OTHER)
    @PostMapping("/send")
    public AjaxResult send() {
        //查询 活动配置
        String param = activityService.getActivityConfig();
        //发送 活动配置
        String result = HttpUtils.sendPost(managerConfig.getGameDomain(), "data="+param);
        if (!"scuess".equals(result)) {
            return AjaxResult.error(result);
        }
        return AjaxResult.success("发送成功");
    }
}
