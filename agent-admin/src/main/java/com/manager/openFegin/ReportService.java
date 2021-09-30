package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/09/11
 */
@FeignClient(value = "data-manager")
public interface ReportService {

    /**
     * 操作用户 金币
     *
     * @param cmd    "addcoins"=加金币 “reducecoins”=减金币
     * @param value  操作金币数  元*10000
     * @param uid    玩家uid
     * @param reason 100073=银行卡充值 100070=vip充值 100071=金卡月卡充值 100072=银卡月卡充值
     *               eg: data={“cmd”:”addcoins”,”value”:1000001,”uid”:105519}
     * @return 操作后金币数 元*10000
     */
    @PostMapping("/data/coins/edit")
    AjaxResult editCoins(@RequestParam("cmd") String cmd, @RequestParam("value") Long value, @RequestParam("uid") Integer uid, @RequestParam("reason") Integer reason);

    /**
     * 给用户 发邮件
     *
     * @param cmd      邮件内容
     * @param mailType 1=全服邮件 2=指定玩家邮件
     * @param range    玩家uid
     *                 eg: data={"cmd":"notifynewmail","mail_type":2,"range":“105519,105520"}
     * @return
     */
    @PostMapping("/data/mail/send")
    AjaxResult sendEmail(@RequestParam("cmd") String cmd, @RequestParam("mailType") Integer mailType, @RequestParam("range") String range);

    /**
     * 踢人
     * eg:data={“cmd”:”forbidden”,”reason”:“游戏里里骂人”,”uid”:105519}
     */
    @PostMapping("/data/user/control")
    AjaxResult control(@RequestParam("cmd") String cmd, @RequestParam("uid") Integer uid, @RequestParam("reason") String reason);

}
