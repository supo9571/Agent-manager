package com.manager.system.mapper;

import com.manager.common.core.domain.entity.StopTakingNotice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 停服公告
 *
 * @author sieGuang 2021/09/27
 */
@Mapper
public interface StopTakingNoticeMapper {

    /**
     * 添加
     */
    Integer addStopTakingNotice(StopTakingNotice stopTakingNotice);

    /**
     * 查询
     */
    List<StopTakingNotice> listStopTakingNotice(StopTakingNotice stopTakingNotice);

    /**
     * 编辑
     */
    Integer editStopTakingNotice(StopTakingNotice stopTakingNotice);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_stop_taking_notice where id = #{id}")
    Integer delStopTakingNotice(@Param("id") String id);


}
