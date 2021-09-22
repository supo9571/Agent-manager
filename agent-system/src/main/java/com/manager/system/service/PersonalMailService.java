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
     * 通过id删除当前数据
     */
    Integer delPersonalMail(String id);

}
