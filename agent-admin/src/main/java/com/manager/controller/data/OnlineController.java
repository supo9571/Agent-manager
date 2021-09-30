package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.OnlinePlayer;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/21
 */
@RestController
@Api(tags = "在线玩家")
@RequestMapping("/data/online")
public class OnlineController {
    @Autowired
    private DataService dataService;
    /**
     * 获取在线玩家列表
     */
    @PreAuthorize("@ss.hasPermi('data:online:list')")
    @ApiOperation(value = "在线玩家")
    @PostMapping("/list")
    public AjaxResult list(OnlinePlayer onlinePlayer) {
        return dataService.getOnlines(onlinePlayer);
    }


}
