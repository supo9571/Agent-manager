package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/7
 */
@Mapper
public interface ActivityMapper {

    Integer saveActivity(Activity activity);

    Integer checkActivityTime(Activity activity);

    List findActivity(Activity activity);

    Integer updateActivity(Activity activity);

    @Select("select activity_begin ac_begin_time,activity_end ac_end_time,sort sort_index,'true' open_state,activity_info ac_content,activity_type ac_type from config_activity " +
            "where activity_begin <=sysdate() <= activity_end and tid = #{tid} order by update_time desc")
    List<Map> getActivityConfig(@Param("tid") Integer tid);

    @Select("select jin_recharge jinRecharge,jin_recharge_give jinRechargeGive,jin_recharge_give_day jinRechargeGiveDay," +
            "yin_recharge yinRecharge,yin_recharge_give yinRechargeGive,yin_recharge_give_day yinRechargeGiveDay " +
            "from config_month_recharge where status = '1' order by update_time desc limit 0,1")
    Map getMonthConfig();
}
