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
     * 充值接口
     *
     */
    @PostMapping("/data/coins/edit")
    AjaxResult editCoins(@RequestParam("amount")Long amount, @RequestParam("ex_coins")Long ex_coins, @RequestParam("uid")Integer uid
            ,@RequestParam("reason")Integer reason ,@RequestParam("other_amount")Long other_amount);

    /**
     * 通知有新邮件
     *
     * mailType : 1=全服邮件 2=指定玩家邮件
     */
    @PostMapping("/data/mail/send")
    AjaxResult sendEmail(@RequestParam("mailType") Integer mailType, @RequestParam("range") String range);

    /**
     * 踢人
     * eg:data={“cmd”:”forbidden”,”reason”:“游戏里里骂人”,”uid”:105519}
     */
    @PostMapping("/data/user/control")
    AjaxResult control(@RequestParam("cmd") String cmd, @RequestParam("uid") Integer uid, @RequestParam("reason") String reason);

    /**
     * 驳回提现请求用户返金币
     * @param uid 用户uid
     * @param coins 加多少钱
     */
    @PostMapping("/data/returnBack")
    AjaxResult returnBack(@RequestParam("uid") Integer uid, @RequestParam("coins") Integer coins);

}
