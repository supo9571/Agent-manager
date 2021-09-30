package com.manager.system.service;

import com.manager.common.core.domain.entity.RotationPicture;

import java.util.List;


/**
 * 轮播图配置
 *
 * @author sieGuang 2021/09/23
 */
public interface RotationPictureService {

    /**
     * 添加
     */
    Integer addRotationPicture(RotationPicture rotationPicture);

    /**
     * 查询
     */
    List listRotationPicture(RotationPicture rotationPicture);

    /**
     * 编辑
     */
    Integer editRotationPicture(RotationPicture rotationPicture);

    /**
     * 通过id删除当前数据
     */
    Integer delRotationPicture(String id);

}
