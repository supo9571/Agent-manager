package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.domain.AjaxResult;

import com.manager.common.core.domain.entity.Game;
import com.manager.common.enums.BusinessType;
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
 * 子游戏管理
 *
 * @author sieGuang 2021/09/03
 */
@RestController
@Api(tags = "游戏管理")
@RequestMapping("/data/game")
public class SubGameController {

    @Autowired
    private SubGameDataService dataService;

    /**
     * 查询
     *
     * @param game 过滤条件
     */
    @PreAuthorize("@ss.hasPermi('data:game:list')")
    @ApiOperation(value = "查询子游戏")
    @PostMapping("/list")
    public AjaxResult getSubGameList(@RequestBody Game game) {
        return dataService.getSubGameList(game);
    }

    /**
     * 编辑
     *
     * @param game 需要修改的内容
     */
    @PreAuthorize("@ss.hasPermi('data:game:edit')")
    @ApiOperation(value = "编辑子游戏")
    @Log(title = "编辑子游戏", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editSubGame(@RequestBody Game game) {
        return dataService.editSubGame(game);
    }
}
