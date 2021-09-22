package com.manager.system.mapper;

import com.manager.common.core.domain.entity.ConfigLanding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


/**
 * @author jason
 * @date 2021-09-20
 */
@Mapper
public interface ConfigLandingMapper {

    //@Select("SELECT id as id, android_url as androidUrl, super_sign_url as superSignUrl,super_sign_status as superSignStatus, enterprise_sign_url as enterpriseSignUrl, enterprise_sign_status as enterpriseSignStatus, tf_sign_url as tfSignUrl, tf_sign_status as tfSignStatus, customer_url as customerUrl FROM config_landing where tid = #{tid}")
    Map getConfigLandingInfo(@Param("tid")Integer tid);

    @Select("select id,tid from config_landing where id = #{id}")
    ConfigLanding getById(@Param("id") Integer id);

    int editConfigLanding(ConfigLanding configLanding);

    int addConfigLanding(ConfigLanding configLanding);

}
