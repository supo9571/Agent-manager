package com.manager.web.controller.game;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.ConfigLanding;
import com.manager.common.utils.StringUtils;
import com.manager.system.service.ConfigLandingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author jason
 * @date 2021-09-20
 */
@Api(tags = "落地页配置")
@RestController
@RequestMapping(value = "/config/landing")
public class ConfigLandingController extends BaseController {

    @Resource
    private ConfigLandingService configLandingService;

    /**
     * 落地页配置
     *
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:config:landing')")
    @ApiOperation(value = "落地页配置查询")
    @PostMapping("/getConfigLanding")
    public AjaxResult getConfigLanding() {
        return AjaxResult.success("查询成功", configLandingService.getConfigLanding(ManagerConfig.getTid()));
    }


    @ApiOperation(value = "编辑落地页配置")
    @PostMapping("/editConfigLanding")
    public AjaxResult editConfigLanding(ConfigLanding configLanding) {
        ConfigLanding configId = configLandingService.getId(configLanding.getId());
        if (configId == null) {
            return AjaxResult.error("数据不存在！");
        }
        return AjaxResult.success(configLandingService.editConfigLanding(configLanding));
    }
}
