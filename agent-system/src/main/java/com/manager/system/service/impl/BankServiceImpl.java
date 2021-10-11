package com.manager.system.service.impl;

import com.manager.common.core.redis.RedisCache;
import com.manager.system.mapper.BankMapper;
import com.manager.system.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/10/11
 */
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankMapper bankMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List getBankList() {
        List list = redisCache.getCacheObject("BankList");
        if(list==null || list.size()<0 ){
            list = bankMapper.getBankList();
            redisCache.setCacheObject("BankList",list);
        }
        return list;
    }
}
