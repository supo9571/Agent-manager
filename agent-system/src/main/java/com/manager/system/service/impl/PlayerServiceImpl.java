package com.manager.system.service.impl;

import com.manager.system.mapper.PlayerMapper;
import com.manager.system.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/11/3
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;
    @Override
    public List getPopularizes(String uid) {
        return playerMapper.getPopularizes(uid);
    }
}
