package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.enums.BusinessType;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/19
 */
@RestController
@Api(tags = "玩家列表")
@RequestMapping("/data/player")
public class PlayerController extends BaseController {

    @Autowired
    private DataService dataService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "查询玩家列表")
    @GetMapping("/list")
    public AjaxResult list(PlayUser playUser) {
        return dataService.getPlayers(playUser);
    }

    /**
     * 查询玩家余额
     */
    @PreAuthorize("@ss.hasPermi('data:player:curr')")
    @ApiOperation(value = "查询玩家余额")
    @GetMapping("/curr")
    public AjaxResult curr(Long uid) {
        return dataService.getPlayerCurr(uid);
    }


    /**
     * 修改玩家信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "修改玩家信息")
    @Log(title = "修改玩家信息", businessType = BusinessType.UPDATE)
    @GetMapping("/edit")
    public AjaxResult edit(PlayUser playUser) {
        return dataService.updatePlayer(playUser);
    }
}
