package com.manager.system.service;

import com.manager.common.core.domain.entity.Ysinfo;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/23
 */
public interface YSService {

    Ysinfo selectByName(String username);

    List getOrder(String uid, String beginTime, String endTime, Integer ysid);

    Map getOrderCount(String uid, String beginTime, String endTime, Integer ysid);

    List getReport(String beginTime, String endTime, Integer ysid);

    String recharge(Integer ysid, Integer uid, Long amount, String name, String bank);

    String risk(Integer ysid, Integer uid, Long amount, String name, String bank);

    List getMark(Integer uid, String beginTime, String endTime);

    List getBlack(Integer uid, String beginTime, String endTime);
}
