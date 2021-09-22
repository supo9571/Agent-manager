package com.manager.system.mapper;

import com.manager.common.core.domain.entity.MailRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 邮箱记录
 * @author sieGuang 2021/09/20
 */
@Mapper
public interface MailRecordMapper {

    /**
     * 查询
     */
    List<MailRecord> listMailRecord(MailRecord mailRecord);

}
