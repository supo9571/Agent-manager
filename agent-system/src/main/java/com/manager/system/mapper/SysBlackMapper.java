package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysBlack;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/25
 */
public interface SysBlackMapper {

    int insertBlack(SysBlack sysBlack);

    List<Map> getSysBlacks(SysBlack sysBlack);

    @Delete("delete from sys_black where id = #{id}")
    void deleteSysBlack(@Param("id") Integer id);
}
