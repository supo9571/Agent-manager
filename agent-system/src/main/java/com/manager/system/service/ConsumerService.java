package com.manager.system.service;

import com.manager.common.core.domain.entity.Consumer;
import java.util.List;

/**
 * 客服信息管理
 * @author sieGuang 2021/09/03
 */
public interface ConsumerService {

    /**
     * 查询
     */
    List getConsumerList();

    /**
     * 新增
     * @param consumer 新增的内容
     */
    int addConsumer(Consumer consumer);

    /**
     * 删除当前数据
     * @param id 主键id
     */
    Integer delConsumer(String id);

    /**
     * 编辑
     * @param consumer 需要修改的内容
     */
    int editConsumer(Consumer consumer);

}
