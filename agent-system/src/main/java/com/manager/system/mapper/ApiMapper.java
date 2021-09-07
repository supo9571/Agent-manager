package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/7
 */
@Mapper
public interface ApiMapper {
    @Select("select jin_recharge,jin_recharge_give jin_recharge_to_give,jin_recharge_give_day jin_recharge_to_give_day," +
            "yin_recharge,yin_recharge_give yin_recharge_to_give,yin_recharge_give_day yin_recharge_give_day," +
            "open_type jump_type,live_addr jump_url,wechat wx,qq  " +
            "from config_month_recharge where status = '1' order by update_time limit 0,1 ")
    Map getMonthConfig();

    @Select("select id,cname,pay_channel,pay_type,recharge_give recharge_to_give,sort sort_id,pay_channel pay_name from config_pay where status = '1'")
    List<Map> selectConfigPay();

    @Select("SELECT open_type,recharge_other_type,recharge_type,IF(open_type = '1',live_addr,url)url,img_type vip_img,vip_name,QQ,wechat FROM config_vip_recharge " +
            " WHERE pay_id = #{id} AND status = '1'")
    List<Map> selectVipconfig(@Param("id") Integer id);

    @Select("SELECT open_type jump_type,btn,live_addr url,bank_value FROM config_bank_recharge where pay_id = #{id} AND status = '1'")
    List<Map> selectBankconfig(@Param("id") Integer id);

    @Select("SELECT btn,diy_max,diy_min,is_diy,msg,other_name,online_config_id,pay_type pay_channel,pay_channel_code FROM config_online_recharge where pay_id = #{id} AND status = '1' ")
    List<Map> selectOnlineconfig(@Param("id") Integer id);
}
