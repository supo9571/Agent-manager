package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Consumer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客服信息管理
 *
 * @author sieGuang 2021/09/06
 */
@Mapper
public interface ConsumerMapper {

    /**
     * 查询
     */
    List<Map> getConsumerList(Integer tid);

    /**
     * 新增
     *
     * @param consumer 新增的内容
     */
    Integer addConsumer(Consumer consumer);

    /**
     * 删除当前数据
     *
     * @param id 主键id
     */
    @Delete("delete from config_consumer where id = #{id}")
    Integer delConsumer(@Param("id") String id);

    /**
     * 编辑
     *
     * @param consumer 需要修改的内容
     */
    int editConsumer(Consumer consumer);

}
