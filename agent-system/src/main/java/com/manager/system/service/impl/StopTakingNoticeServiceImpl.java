package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.StopTakingNotice;
import com.manager.system.mapper.StopTakingNoticeMapper;
import com.manager.system.service.StopTakingNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 停服公告
 * @author sieGuang 2021/09/27
 */
@Service
public class StopTakingNoticeServiceImpl implements StopTakingNoticeService {

    @Autowired
    private StopTakingNoticeMapper stopTakingNoticeMapper;

    @Override
    public Integer addStopTakingNotice(StopTakingNotice stopTakingNotice) {
        return stopTakingNoticeMapper.addStopTakingNotice(stopTakingNotice);
    }

    @Override
    public List listStopTakingNotice(StopTakingNotice stopTakingNotice) {
        return stopTakingNoticeMapper.listStopTakingNotice(stopTakingNotice);
    }

    @Override
    public Integer editStopTakingNotice(StopTakingNotice stopTakingNotice) {
        return stopTakingNoticeMapper.editStopTakingNotice(stopTakingNotice);
    }


    @Override
    public Integer delStopTakingNotice(String id) {
        return stopTakingNoticeMapper.delStopTakingNotice(id);
    }

}
