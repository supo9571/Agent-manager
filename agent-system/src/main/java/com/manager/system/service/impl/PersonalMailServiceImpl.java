package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.PersonalMail;
import com.manager.system.mapper.PersonalMailMapper;
import com.manager.system.service.PersonalMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人邮箱配置
 * @author sieGuang 2021/09/20
 */
@Service
public class PersonalMailServiceImpl implements PersonalMailService {

    @Autowired
    private PersonalMailMapper personalMailMapper;

    @Override
    public Integer addPersonalMail(PersonalMail personalMail) {
        return personalMailMapper.addPersonalMail(personalMail);
    }

    @Override
    public List listPersonalMail(PersonalMail personalMail) {
        return personalMailMapper.listPersonalMail(personalMail);
    }

    @Override
    public Integer editPersonalMail(PersonalMail personalMail) {
        return personalMailMapper.editPersonalMail(personalMail);
    }

    @Override
    public Integer delPersonalMail(String id) {
        return personalMailMapper.delPersonalMail(id);
    }

}
