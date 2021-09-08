package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/9/7
 */
@Mapper
public interface ActivityMapper {

    Integer saveActivity(Activity activity);

    Integer checkActivityTime(Activity activity);

    List findActivity(Activity activity);

    Integer updateActivity(Activity activity);
}
