package com.manager.system.service;

import com.manager.common.core.domain.entity.*;

import java.util.List;


/**
 * 系统公告配置
 *
 * @author sieGuang 2021/09/18
 */
public interface SystemNoticeService {

    /**
     * 添加
     */
    Integer addSystemNotice(SystemNotice systemNotice);

    /**
     * 查询
     */
    List listSystemNotice(SystemNotice systemNotice);

    /**
     * 编辑
     */
    Integer editSystemNotice(SystemNotice systemNotice);

    /**
     * 下线
     */
    Integer offlineSystemNotice(SystemNotice systemNotice);

    /**
     * 通过id删除当前数据
     */
    Integer delSystemNotice(String id);

}
