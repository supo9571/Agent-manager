package com.manager.system.service;

import com.manager.common.core.domain.entity.StopTakingNotice;

import java.util.List;


/**
 * 停服公告
 *
 * @author sieGuang 2021/09/27
 */
public interface StopTakingNoticeService {

    /**
     * 添加
     */
    Integer addStopTakingNotice(StopTakingNotice stopTakingNotice);

    /**
     * 查询
     */
    List listStopTakingNotice(StopTakingNotice stopTakingNotice);

    /**
     * 编辑
     */
    Integer editStopTakingNotice(StopTakingNotice stopTakingNotice);


    /**
     * 通过id删除当前数据
     */
    Integer delStopTakingNotice(String id);

}
