package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.PersonalMail;
import com.manager.common.utils.DateUtils;
import com.manager.common.utils.SecurityUtils;
import com.manager.system.mapper.PersonalMailMapper;
import com.manager.system.service.PersonalMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 个人邮箱配置
 *
 * @author sieGuang 2021/09/20
 */
@Service
public class PersonalMailServiceImpl implements PersonalMailService {

    @Autowired
    private PersonalMailMapper personalMailMapper;

    @Override
    public Integer addPersonalMail(PersonalMail personalMail) {
        // 判断当前时间是否大于发送时间
        if (DateUtils.dateCompare(personalMail.getSendOutTime())) {
            personalMail.setState("2");
        } else {
            personalMail.setState("1");
        }
        return personalMailMapper.addPersonalMail(personalMail);
    }

    @Override
    public List listPersonalMail(PersonalMail personalMail) {
        List<PersonalMail> list = personalMailMapper.listPersonalMail(personalMail);
        // 状态不等于已下线 且 判断当前时间是否大于发送时间
        list.forEach(l ->{
            if ((!"3".equals(l.getState())) && DateUtils.dateCompare(l.getSendOutTime())) {
                l.setState("2");
            }
        });
        return list;
    }

    @Override
    public Integer editPersonalMail(PersonalMail personalMail) {
        // 判断当前时间是否大于发送时间
        if (DateUtils.dateCompare(personalMail.getSendOutTime())) {
            personalMail.setState("2");
        } else {
            personalMail.setState("1");
        }
        return personalMailMapper.editPersonalMail(personalMail);
    }

    @Override
    public Integer offlinePersonalMail(Integer id, Integer type) {
        PersonalMail personalMail = new PersonalMail();
        personalMail.setId(id);
        personalMail.setState("3");
        personalMail.setTid(ManagerConfig.getTid());
        personalMail.setUpdateBy(SecurityUtils.getUsername());

        Integer i = 0;

        // 1下线 2测回
        if (type == 1) {
            i = personalMailMapper.editPersonalMail(personalMail);
        } else {
            // 更新 个人邮箱配置表状态
            i = personalMailMapper.editPersonalMail(personalMail);

            // 防止异常
            if (i <= 0) {
                return i;
            }
            // 更新 邮箱记录状态
            personalMailMapper.editMailRecord(id, ManagerConfig.getTid(), SecurityUtils.getUsername());
        }
        return i;
    }


    @Override
    public Integer delPersonalMail(String id) {
        return personalMailMapper.delPersonalMail(id);
    }

}
