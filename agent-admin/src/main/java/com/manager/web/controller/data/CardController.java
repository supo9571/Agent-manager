package com.manager.web.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Card;
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
@Api(tags = "牌局记录")
@RequestMapping("/data/card")
public class CardController {

    @Autowired
    private DataService dataService;
    /**
     * 获取牌局记录 列表
     */
    @PreAuthorize("@ss.hasPermi('data:card:list')")
    @ApiOperation(value = "查询牌局记录列表")
    @GetMapping("/list")
    public AjaxResult list(Card card) {
        return dataService.getCards(card);
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:card:list')")
    @ApiOperation(value = "查询牌局记录详情")
    @GetMapping("/info")
    public AjaxResult info(String tableGid) {
        return dataService.findCardInfo(tableGid);
    }
}
