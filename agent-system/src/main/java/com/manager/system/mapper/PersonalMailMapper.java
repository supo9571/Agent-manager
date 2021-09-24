package com.manager.system.mapper;

import com.manager.common.core.domain.entity.PersonalMail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 个人邮箱配置
 * @author sieGuang 2021/09/20
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
     * 更新 个人邮箱配置表状态
     */
    Integer editPersonalMail(PersonalMail personalMail);

    /**
     * 更新 邮箱记录状态
     * @param id
     */
    Integer editMailRecord(@Param("id") Integer id,@Param("tid") Integer tid,@Param("updateBy") String updateBy);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_personal_mail where id = #{id}")
    Integer delPersonalMail(@Param("id") String id);


}
