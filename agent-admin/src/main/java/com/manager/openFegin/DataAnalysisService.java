package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.AddUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 数据分析
 * @author sieGuang 2021/11/6
 */
@FeignClient(value = "data-manager")
public interface DataAnalysisService {

    @PostMapping(value = "/data/addUser/list")
    AjaxResult getAddUser(@RequestBody AddUser addUser);

}
