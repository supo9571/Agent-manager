package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Ysinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/23
 */
@Mapper
public interface YSMapper {
    @Select("select id,password,username,ip_white_list ipWhiteList, " +
            "google_check googleCheck,google_key googleKey from config_ys where username = #{username} and status=1 and tid = #{tid}")
    Ysinfo selectByName(@Param("username") String username,@Param("tid") Integer tid);

    List getOrder(@Param("uid") String uid,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("ysid") Integer ysid);

    Map getOrderCount(@Param("uid") String uid,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("ysid") Integer ysid);

    List getReport(@Param("beginTime")String beginTime,@Param("endTime") String endTime,@Param("ysid") Integer ysid);

    @Select("select amount/10000 from config_ys where id = #{ysid}")
    BigDecimal getYsAmount(@Param("ysid")Integer ysid);

    @Select("SELECT count(1) FROM data_register WHERE uid = #{uid} AND channel IN (SELECT t_id from sys_tenant where t_type = '2' and tenant = #{tid})")
    Integer checkUid(@Param("uid")Integer uid, @Param("tid")Integer tid);

    @Select("SELECT count(1) FROM user_black WHERE name = #{name} AND bank = #{bank} and tid = #{tid}")
    Integer checkBlack(@Param("name")String name,@Param("bank") String bank,@Param("tid") Integer tid);

    @Select("SELECT count(1) FROM user_exchange WHERE uid = #{uid} and name = #{name} ")
    Integer selectExchangeName(@Param("uid") Integer uid,@Param("name") String name);

    @Update("update config_ys set amount = amount - #{amount} where id = #{ysid}")
    void updateYSAmount(@Param("ysid") Integer ysid,@Param("amount") long amount);

    List getMark(@Param("uid") Integer uid,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("tid") Integer tid);

    List getBlack(@Param("uid") Integer uid,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("tid") Integer tid);

    void saveMark(@Param("uid") Integer uid,@Param("name")String name,@Param("bank")String bank,@Param("tid") Integer tid);
}
