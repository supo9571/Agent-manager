package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/27
 */
@RestController
@Api(tags = "游戏管理")
@RequestMapping("/data/game")
public class GameController {

    @Autowired
    private DataService dataService;

    /**
     * 获取游戏下拉框
     */
    @ApiOperation(value = "获取游戏下拉列表")
    @GetMapping("/option")
    public AjaxResult option() {
        return dataService.getGames(ManagerConfig.getTid());
    }

    /**
     * 添加 测试ip
     */
    @ApiOperation(value = "添加测试ip")
    @Log(title = "添加测试ip", businessType = BusinessType.INSERT)
    @GetMapping("/addIp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "测试ip")
    })
    public AjaxResult addIp(String ip) {
        return dataService.addIp(ip, SecurityUtils.getUsername());
    }

    /**
     * 查询 测试ip
     */
    @ApiOperation(value = "查询测试ip")
    @GetMapping("/findIp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "测试ip"),
            @ApiImplicitParam(name = "createBy", value = "操作人"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    public AjaxResult findIp(String ip, String createBy, String beginTime, String endTime) {
        return dataService.findIp(ip, createBy, beginTime, endTime);
    }

    /**
     * 删除 测试ip
     */
    @ApiOperation(value = "删除测试ip")
    @GetMapping("/delIp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id")
    })
    public AjaxResult delIp(Integer id) {
        return dataService.delIp(id);
    }
}
