package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.service.ReportAgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 游戏端服务
 * @author sieGuang 2021/11/09
 */
@Slf4j
@Service
public class ReportAgentServiceImpl implements ReportAgentService {

    @Autowired
    private ManagerConfig managerConfig;

    @Override
    public AjaxResult editCoinsGm(String cmd, Long value, Integer uid, Integer saveflag, Integer reason) {
        JSONObject param = new JSONObject();
        param.put("cmd", cmd);
        param.put("value", value);
        param.put("uid", uid);
        param.put("saveflag", saveflag);
        param.put("reason", reason);
        //操作 用户金币
        String result = HttpUtils.sendPost(managerConfig.getDomainIp() + managerConfig.getChangeCoins(),
                "data=" + param.toJSONString());

        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //返回操作后金额
            return AjaxResult.success("操作成功", resultJson.getLong("curr"));
        }
        log.error("操作用户金币失败;参数:{};返回值:{}", param, result);
        return AjaxResult.error();
    }

}