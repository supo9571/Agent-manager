package com.manager.controller;

import com.manager.common.config.ManagerConfig;
import com.manager.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 首页
 *
 * @author marvin
 */
@RestController
@ApiIgnore
public class IndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}银商系统，当前版本：v{}(♥◠‿◠)ﾉﾞ", managerConfig.getName(), managerConfig.getVersion());
    }
}
