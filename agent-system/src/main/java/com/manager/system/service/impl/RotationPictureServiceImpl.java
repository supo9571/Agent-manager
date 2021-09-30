package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.RotationPicture;
import com.manager.system.mapper.RotationPictureMapper;
import com.manager.system.service.RotationPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图配置
 * @author sieGuang 2021/09/23
 */
@Service
public class RotationPictureServiceImpl implements RotationPictureService {

    @Autowired
    private RotationPictureMapper rotationPictureMapper;

    @Override
    public Integer addRotationPicture(RotationPicture rotationPicture) {
        return rotationPictureMapper.addRotationPicture(rotationPicture);
    }

    @Override
    public List listRotationPicture(RotationPicture rotationPicture) {
        return rotationPictureMapper.listRotationPicture(rotationPicture);
    }

    @Override
    public Integer editRotationPicture(RotationPicture rotationPicture) {
        return rotationPictureMapper.editRotationPicture(rotationPicture);
    }

    @Override
    public Integer delRotationPicture(String id) {
        return rotationPictureMapper.delRotationPicture(id);
    }

}
