package com.manager.system.mapper;

import com.manager.common.core.domain.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/6
 */
@Mapper
public interface RechargeMapper {

    Integer saveVipRecharge(VipRecharge vipRecharge);

    List findVipRecharge(@Param("tid") Integer tid);

    Integer updateVipRecharge(VipRecharge vipRecharge);

    Integer saveOnlineRecharge(OnlineRecharge onlineRecharge);

    @Select("select id payId,cname payName from config_pay where pay_type = '2' and tid = #{tid} ")
    List<Map> getOnlinePays(@Param("tid") Integer tid);

    List findOnlineRecharge(OnlineRecharge onlineRecharge);

    Integer updateOnlineRecharge(OnlineRecharge onlineRecharge);

    Integer saveBankRecharge(BankRecharge bankRecharge);

    List findBankRecharge(BankRecharge bankRecharge);

    Integer updateBankRecharge(BankRecharge bankRecharge);

    Integer saveMonthRecharge(MonthRecharge monthRecharge);

    List findMonthRecharge(MonthRecharge monthRecharge);

    Integer updateMonthRecharge(MonthRecharge monthRecharge);

    Integer saveYsinfo(Ysinfo ysinfo);

    @Select("select id,username,password,amount,update_time updateTime,google_check googleCheck,google_key googleKey,recharge_status rechargeStatus from config_ys where status = '1' and tid =#{tid} ")
    List<Map> fingYsinfo(@Param("tid") Integer tid);

    Integer updateYsinfo(Ysinfo ysinfo);

    @Select("select id,username from config_ys where status = '1' and tid =#{tid} ")
    List<Map> getYsOption(@Param("tid") Integer tid);

    @Update("update config_ys set amount = amount+#{amount} where id = #{ysid} ")
    void ysRecharge(@Param("ysid") Integer ysid, @Param("amount") Long amount);

    YsQuota findYsinfoById(@Param("ysid") Integer ysid);

    void addYsQuotaInfo(YsQuota ysQuota);

    List<Map> getYsQuotaInfo(@Param("ysid") Integer ysid,@Param("type") Integer type);
}
