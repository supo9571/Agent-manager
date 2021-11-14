package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Game;
import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;
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

    @PostMapping(value = "/data/control/player/add", consumes = "application/json")
    AjaxResult add(@RequestBody ControlPlayer controlPlayer);

    @PostMapping(value = "/data/control/player/list", consumes = "application/json")
    AjaxResult list(@RequestBody ControlPlayer controlPlayer);

    @PostMapping(value = "/data/control/player/edit", consumes = "application/json")
    AjaxResult edit(@RequestBody ControlPlayer controlPlayer);

    @PostMapping(value = "/data/control/player/del", consumes = "application/json")
    AjaxResult del(@RequestBody ControlPlayer controlPlayer);

    @PostMapping(value = "/data/control/player/infoList", consumes = "application/json")
    AjaxResult infoList(@RequestBody ControlPlayerInfo controlPlayerInfo);

    @PostMapping(value = "/data/game/list", consumes = "application/json")
    AjaxResult getSubGameList(@RequestBody Game game);

    @PostMapping(value = "/data/game/edit", consumes = "application/json")
    AjaxResult editSubGame(@RequestBody Game game);
}
