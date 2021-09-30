package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SystemNotice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统公告配置
 *
 * @author sieGuang 2021/09/18
 */
@Mapper
public interface SystemNoticeMapper {

    /**
     * 添加
     */
    Integer addSystemNotice(SystemNotice systemNotice);

    /**
     * 查询
     */
    List<SystemNotice> listSystemNotice(SystemNotice systemNotice);

    /**
     * 编辑
     */
    Integer editSystemNotice(SystemNotice systemNotice);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_system_notice where id = #{id}")
    Integer delSystemNotice(@Param("id") String id);


}
