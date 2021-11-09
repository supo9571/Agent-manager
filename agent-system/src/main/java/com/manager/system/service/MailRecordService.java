package com.manager.system.service;

import com.manager.common.core.domain.entity.MailRecord;

import java.util.List;


/**
 * 邮箱记录
 *
 * @author sieGuang 2021/09/20
 */
public interface MailRecordService {

    /**
     * 查询
     */
    List listMailRecord(MailRecord mailRecord);

    void saveMailRecord(MailRecord mailRecord);

    /**
     * 发送邮件
     */
    void sendOutMail(MailRecord mail);
}
