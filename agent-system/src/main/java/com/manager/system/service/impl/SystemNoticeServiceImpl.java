package com.manager.system.service.impl;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.SystemNotice;
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
public class SystemNoticeServiceImpl implements SystemNoticeService {

    @Autowired
    private SystemNoticeMapper systemNoticeMapper;

    @Override
    public Integer addSystemNotice(SystemNotice systemNotice) {
        return systemNoticeMapper.addSystemNotice(systemNotice);
    }

    @Override
    public List listSystemNotice(SystemNotice systemNotice) {
        systemNotice.setTid(ManagerConfig.getTid());
        return systemNoticeMapper.listSystemNotice(systemNotice);
    }

    @Override
    public Integer editSystemNotice(SystemNotice systemNotice) {
        return systemNoticeMapper.editSystemNotice(systemNotice);
    }

    @Override
    public Integer delSystemNotice(String id) {
        return systemNoticeMapper.delSystemNotice(id);
    }

}
