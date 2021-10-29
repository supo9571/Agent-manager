package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysBlack;
import com.manager.common.core.domain.entity.SysBlackInfo;
import com.manager.common.core.domain.model.UserLock;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    List getSysBlackInfos(SysBlackInfo sysBlackInfo);

    @Update("update sys_black_info set handle_type = '4' where id = #{id}")
    void readSysBlackInfos(@Param("id") int id);

    @Update("update sys_black_info set handle_type = '3',create_by = #{name} where id = #{id}")
    void sealSysBlackInfos(@Param("id") int id,String name);

    SysBlackInfo getSysBlackInfo(@Param("id") int id);

    void saveUserLock(UserLock userLock);

    void saveUserLockLog(UserLock userLock);

    @Select("select count(1) from sys_black where t_id = #{tid} and black_type = #{blackType} and handle_type = #{handleType} and black_num = #{blackNum}")
    int check(SysBlack sysBlack);
}
