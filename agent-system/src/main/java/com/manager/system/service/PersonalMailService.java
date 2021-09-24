package com.manager.system.service;

import com.manager.common.core.domain.entity.PersonalMail;

import java.util.List;


/**
 * 个人邮箱配置
 * @author sieGuang 2021/09/20
 */
public interface PersonalMailService {

    /**
     * 添加
     */
    Integer addPersonalMail(PersonalMail personalMail);

    /**
     * 查询
     */
    List listPersonalMail(PersonalMail personalMail);

    /**
     * 编辑
     */
    Integer editPersonalMail(PersonalMail personalMail);

    /**
     * 下线 通过id 把当前数据状态改成 线下状态
     * 测回 还需要把id 对应的邮箱记录表数据状态 改成 测回状态
     */
    Integer offlinePersonalMail(Integer id,Integer type);

    /**
     * 通过id删除当前数据
     */
    Integer delPersonalMail(String id);

}
