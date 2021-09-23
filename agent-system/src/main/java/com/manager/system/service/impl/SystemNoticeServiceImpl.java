package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.SystemNotice;
import com.manager.common.utils.DateUtils;
import com.manager.system.mapper.SystemNoticeMapper;
import com.manager.system.service.SystemNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统公告配置
 * @author sieGuang 2021/09/18
 */
@Service
public class SystemNoticeServiceImpl<mian> implements SystemNoticeService {

    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Override
    public Integer addSystemNotice(SystemNotice systemNotice) {
        // 发送状态等于定时发送 且 判断当前时间是否大于发送时间
        if("2".equals(systemNotice.getSendOutTimeType()) && DateUtils.dateCompare(systemNotice.getSendOutTime())){
            systemNotice.setState("1");
        }else{
            systemNotice.setState("2");
        }
        return systemNoticeMapper.addSystemNotice(systemNotice);
    }

    @Override
    public List listSystemNotice(SystemNotice systemNotice) {
        return systemNoticeMapper.listSystemNotice(systemNotice);
    }

    @Override
    public Integer editSystemNotice(SystemNotice systemNotice) {
        // 发送状态等于定时发送 且 判断当前时间是否大于发送时间
        if("2".equals(systemNotice.getSendOutTimeType()) && DateUtils.dateCompare(systemNotice.getSendOutTime())){
            systemNotice.setState("1");
        }else{
            systemNotice.setState("2");
        }
        return systemNoticeMapper.editSystemNotice(systemNotice);
    }

    @Override
    public Integer delSystemNotice(String id) {
        return systemNoticeMapper.delSystemNotice(id);
    }

}
