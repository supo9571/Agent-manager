package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.SecurityUtils;
import com.manager.openFegin.SubGameDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/10/20
 * 玩家风控设置
 */
@RestController
@Api(tags = "玩家风控设置")
@RequestMapping("/data/control/player")
public class ControlPlayerController {

    @Autowired
    private SubGameDataService subGameDataService;


    /**
     * 添加玩家风控设置
     */
    @PreAuthorize("@ss.hasPermi('data:player:add')")
    @ApiOperation(value = "添加玩家风控设置")
    @Log(title = "添加玩家风控设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ControlPlayer controlPlayer) {
        controlPlayer.setTid(ManagerConfig.getTid());
        controlPlayer.setCreateBy(SecurityUtils.getUsername());
        return subGameDataService.add(controlPlayer);
    }

    /**
     * 查询玩家风控设置
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "查询玩家风控设置")
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ControlPlayer controlPlayer) {
        controlPlayer.setTid(ManagerConfig.getTid());
        return subGameDataService.list(controlPlayer);
    }

    /**
     * 修改玩家风控设置
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "修改玩家风控设置")
    @Log(title = "修改玩家风控设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ControlPlayer controlPlayer) {
        controlPlayer.setCreateBy(SecurityUtils.getUsername());
        return subGameDataService.edit(controlPlayer);
    }

    /**
     * 移除玩家风控设置
     */
    @PreAuthorize("@ss.hasPermi('data:player:del')")
    @ApiOperation(value = "移除玩家风控设置")
    @Log(title = "移除玩家风控设置", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    public AjaxResult del(@RequestBody ControlPlayer controlPlayer) {
        controlPlayer.setCreateBy(SecurityUtils.getUsername());
        return subGameDataService.del(controlPlayer);
    }

    /**
     * 查询玩家风控设日志
     */
    @PreAuthorize("@ss.hasPermi('data:player:infoList')")
    @ApiOperation(value = "查询玩家风控设日志")
    @PostMapping("/infoList")
    public AjaxResult infoList(@RequestBody ControlPlayerInfo controlPlayerInfo) {
        controlPlayerInfo.setTid(ManagerConfig.getTid());
        return subGameDataService.infoList(controlPlayerInfo);
    }

}
