package com.manager.system.service.impl;


import com.manager.common.core.domain.entity.Consumer;
import com.manager.system.mapper.ConsumerMapper;
import com.manager.system.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客服信息管理
 * @author sieGuang 2021/09/06
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List getConsumerList() {
        return consumerMapper.getConsumerList();
    }

    @Override
    public int addConsumer(Consumer consumer) {
        return consumerMapper.addConsumer(consumer);
    }

    @Override
    public Integer delConsumer(String id) {
        return consumerMapper.delConsumer(id);
    }

    @Override
    public int editConsumer(Consumer consumer) {
        return consumerMapper.editConsumer(consumer);
    }

}
