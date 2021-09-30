package com.manager.controller.game;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Exchange;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.ExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提现信息管理
 *
 * @author sieGuang 2021/09/07
 */
@RestController
@RequestMapping("/game/exchange")
@Api(tags = "提现信息管理")
public class ExchangeController extends BaseController {

    @Autowired
    private ExchangeService exchangeService;

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:game:listExchange')")
    @ApiOperation(value = "查询客服信息")
    @PostMapping("/listExchange")
    public AjaxResult getExchangeList() {

        // 账户保留金额
        BigDecimal configKeepMoney = new BigDecimal(0);
        // 提现次数
        int configNum = 0;
        // 打码倍数
        int configAddMosaicNum = 0;

        List<Map> list = exchangeService.getExchangeList();
        if (CollectionUtils.isNotEmpty(list)) {
            configKeepMoney = (BigDecimal) list.get(0).get("keepMoney");
            configNum = (int) list.get(0).get("num");
            configAddMosaicNum = (int) list.get(0).get("addMosaicNum");
        }

        Map result = new HashMap();
        result.put("list", list);
        result.put("configKeepMoney", configKeepMoney);
        result.put("configNum", configNum);
        result.put("configAddMosaicNum", configAddMosaicNum);

        return AjaxResult.success("查询成功", result);
    }

    /**
     * 编辑
     *
     * @param exchange 需要修改的内容
     */
    @PreAuthorize("@ss.hasPermi('system:game:editExchange')")
    @ApiOperation(value = "编辑商场页签")
    @Log(title = "编辑商场页签", businessType = BusinessType.UPDATE)
    @PostMapping("/editExchange")
    public AjaxResult editExchange(@RequestBody Exchange exchange) {
        exchange.setTid(ManagerConfig.getTid());
        int i = exchangeService.editExchange(exchange);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    @PreAuthorize("@ss.hasPermi('system:game:editSettingsExchange')")
    @ApiOperation(value = "编辑基础设置")
    @Log(title = "编辑基础设置", businessType = BusinessType.UPDATE)
    @PostMapping("/editSettingsExchange")
    public AjaxResult editSettingsExchange(int settingsType, String value) {
        int i = exchangeService.editSettingsExchange(settingsType, value);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

}
