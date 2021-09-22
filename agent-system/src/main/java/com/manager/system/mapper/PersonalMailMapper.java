package com.manager.system.mapper;

import com.manager.common.core.domain.entity.PersonalMail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统公告配置
 * @author sieGuang 2021/09/18
 */
@Mapper
public interface PersonalMailMapper {

    /**
     * 添加
     */
    Integer addPersonalMail(PersonalMail personalMail);

    /**
     * 查询
     */
    List<PersonalMail> listPersonalMail(PersonalMail personalMail);

    /**
     * 编辑
     */
    Integer editPersonalMail(PersonalMail personalMail);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_personal_mail where id = #{id}")
    Integer delPersonalMail(@Param("id") String id);


}
