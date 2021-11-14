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
     * @param amount    充值单加多少钱,不包含赠送
     * @param ex_coins  赠送多少钱
     * @param uid       玩家id
     * @param reason    100070=vip充值  100071=金卡月卡充值 100072=银卡月卡充值 100073=银行卡充值
     * @param other_amount  最终可得多少钱,针对月卡,充值1000,立得600,最终可得1200,此时这个这段需要传1200
     * @param saveflag  0=充值到大厅,1=充值到保险箱,默认充值到大厅
     */
    @PostMapping("/data/coins/edit")
    AjaxResult editCoins(@RequestParam("amount")Long amount, @RequestParam("ex_coins")Long ex_coins, @RequestParam("uid")Integer uid
            ,@RequestParam("reason")Integer reason ,@RequestParam("other_amount")Long other_amount
            ,@RequestParam("saveflag") Integer saveflag);

    /**
     * 给用户加减金币
     * @param cmd   addcoins 加金币、reducecoins 减金币
     * @param value 增加值
     * @param uid   用户uid
     * @param saveflag 0=操作大厅的金币,1=操作保险箱,默认0
     * @param reason 100070=vip充值  100071=金卡月卡充值 100072=银卡月卡充值 100073=银行卡充值
     */
    @PostMapping("/data/coins/editGm")
    AjaxResult editCoinsGm(@RequestParam("cmd") String cmd,@RequestParam("value") Long value
            ,@RequestParam("uid") Integer uid,@RequestParam("saveflag") Integer saveflag,@RequestParam("reason") Integer reason);

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
