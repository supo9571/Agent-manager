package com.manager.controller.news;

import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.MailRecord;
import com.manager.system.service.MailRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 邮箱记录
 * @author sieGuang 2021/09/20
 */
@RestController
@RequestMapping("/news/mailRecord")
@Api(tags = "邮箱记录")
public class MailRecordController extends BaseController {

    @Autowired
    private MailRecordService mailRecordService;

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:news:listMailRecord')")
    @ApiOperation(value = "查询邮箱记录列表")
    @PostMapping("/listMailRecord")
    public AjaxResult listMailRecord(@RequestBody MailRecord mailRecord) {
        startPage(mailRecord.getPage(),mailRecord.getSize(),mailRecord.getOrderByColumn(),mailRecord.getIsAsc());
        mailRecord.setTid(ManagerConfig.getTid());
        List list = mailRecordService.listMailRecord(mailRecord);
        return AjaxResult.success(getDataTable(list,mailRecord.getPage(),mailRecord.getSize()));
    }

}
