package com.manager.system.service;

import com.manager.common.core.domain.entity.Propaganda;

import java.util.List;


/**
 * 宣传活动配置
 * @author sieGuang 2021/09/25
 */
public interface PropagandaService {

    /**
     * 添加
     */
    Integer addPropaganda(Propaganda propaganda);

    /**
     * 查询
     */
    List listPropaganda(Propaganda propaganda);

    /**
     * 编辑
     */
    Integer editPropaganda(Propaganda propaganda);


    /**
     * 通过id删除当前数据
     */
    Integer delPropaganda(String id);

}
