package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.Activity;
import com.manager.system.mapper.ActivityMapper;
import com.manager.system.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/7
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public Integer saveActivity(Activity activity) {
        return activityMapper.saveActivity(activity);
    }

    /**
     * 判断 活动时间 是否重叠
     * @param activity
     * @return
     */
    @Override
    public Integer checkActivityTime(Activity activity) {
        return activityMapper.checkActivityTime(activity);
    }

    @Override
    public List findActivity(Activity activity) {
        return activityMapper.findActivity(activity);
    }

    @Override
    public Integer updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }
}
