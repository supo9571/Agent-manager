package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author marvin 2021/10/7
 */
@FeignClient(value = "data-manager")
public interface SubGameDataService {

    @PostMapping(value = "/data/subgame/list", consumes = "application/json")
    AjaxResult getSubGameDate(@RequestBody SubGameData subGameData);

    @PostMapping(value = "/data/subgame/table", consumes = "application/json")
    AjaxResult getTableDate(@RequestBody SubGameData subGameData);

}
