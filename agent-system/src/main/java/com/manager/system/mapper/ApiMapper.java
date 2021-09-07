package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
