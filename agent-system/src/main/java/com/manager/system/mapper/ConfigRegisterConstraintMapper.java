package com.manager.system.mapper;

import com.manager.common.core.domain.entity.ConfigRegisterConstraint;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author jason
 * @date 2021-11-06
 */
@Mapper
public interface ConfigRegisterConstraintMapper {

    @Select("select tid,ip_num ipNum ,device_num deviceNum from config_register_constraint where tid = #{tid}")
    ConfigRegisterConstraint getConfigRegisterConstraint(Integer tid);

    @Insert("insert into config_register_constraint(tid,ip_num,device_num) values(#{tid},#{ipNum},#{deviceNum})")
    Integer saveConfigRegisterConstraint(ConfigRegisterConstraint configRegisterConstraint);

    @Update("update config_register_constraint set ip_num = #{ipNum}, device_num =#{deviceNum} where  tid = #{tid}")
    int updateConfigRegisterConstraint(ConfigRegisterConstraint configRegisterConstraint);

}
