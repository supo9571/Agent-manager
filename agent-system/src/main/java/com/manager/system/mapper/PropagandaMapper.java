package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Propaganda;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 宣传活动配置
 *
 * @author sieGuang 2021/09/25
 */
@Mapper
public interface PropagandaMapper {

    /**
     * 添加
     */
    Integer addPropaganda(Propaganda propaganda);

    /**
     * 查询
     */
    List<Propaganda> listPropaganda(Propaganda propaganda);

    /**
     * 编辑
     */
    Integer editPropaganda(Propaganda propaganda);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_propaganda where id = #{id}")
    Integer delPropaganda(@Param("id") String id);


}
