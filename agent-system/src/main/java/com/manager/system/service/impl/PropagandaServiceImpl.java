package com.manager.system.service.impl;

import com.manager.common.annotation.DataSource;
import com.manager.common.core.domain.entity.Propaganda;
import com.manager.common.enums.DataSourceType;
import com.manager.common.utils.DateUtils;
import com.manager.system.mapper.PropagandaMapper;
import com.manager.system.service.PropagandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 宣传活动配置
 * @author sieGuang 2021/09/25
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class PropagandaServiceImpl implements PropagandaService {

    @Autowired
    private PropagandaMapper propagandaMapper;

    @Override
    public Integer addPropaganda(Propaganda propaganda) {
        // 当前时间 > 开始时间
        if(DateUtils.dateCompare(propaganda.getBeginTime())){
            // 当前时间 < 结束数据  = 在线
            // 当前时间 > 结束数据  = 下线（else）
            if(!(DateUtils.dateCompare(propaganda.getEndTime()))){
                propaganda.setState("2");
            }else{
                propaganda.setState("3");
            }
        }else{
            // 其它 = 待发送
            propaganda.setState("1");
        }
        return propagandaMapper.addPropaganda(propaganda);
    }

    @Override
    public List listPropaganda(Propaganda propaganda) {
        return propagandaMapper.listPropaganda(propaganda);
    }

    @Override
    public Integer editPropaganda(Propaganda propaganda) {
        // 当前时间 > 开始时间
        if(DateUtils.dateCompare(propaganda.getBeginTime())){
            // 当前时间 < 结束数据  = 在线
            // 当前时间 > 结束数据  = 下线（else）
            if(!(DateUtils.dateCompare(propaganda.getEndTime()))){
                propaganda.setState("2");
            }else{
                propaganda.setState("3");
            }
        }else{
            // 其它 = 待发送
            propaganda.setState("1");
        }
        return propagandaMapper.editPropaganda(propaganda);
    }


    @Override
    public Integer delPropaganda(String id) {
        return propagandaMapper.delPropaganda(id);
    }

}
