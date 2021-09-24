package com.manager.system.mapper;

import com.manager.common.core.domain.entity.RotationPicture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图配置
 * @author sieGuang 2021/09/23
 */
@Mapper
public interface RotationPictureMapper {

    /**
     * 添加
     */
    Integer addRotationPicture(RotationPicture rotationPicture);

    /**
     * 查询
     */
    List<RotationPicture> listRotationPicture(RotationPicture rotationPicture);

    /**
     * 编辑
     */
    Integer editRotationPicture(RotationPicture rotationPicture);

    /**
     * 通过id删除当前数据
     */
    @Delete("delete from sys_rotation_picture where id = #{id}")
    Integer delRotationPicture(@Param("id") String id);

}
