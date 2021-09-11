package com.manager.web.controller.game;
import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Consumer;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客服信息管理
 * @author sieGuang 2021/09/06
 */
@RestController
@RequestMapping("/game/consumer")
@Api(tags = "客服信息管理")
public class ConsumerController extends BaseController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('system:game:listConsumer')")
    @ApiOperation(value = "查询客服信息")
    @PostMapping("/listConsumer")
    public AjaxResult getConsumerList(Integer page) {
        return AjaxResult.success("查询成功", consumerService.getConsumerList());
    }

    /**
     * 添加
     */
    @PreAuthorize("@ss.hasPermi('system:game:addConsumer')")
    @ApiOperation(value = "新增客服信息")
    @Log(title = "新增客服信息", businessType = BusinessType.INSERT)
    @PostMapping("/addConsumer")
    public AjaxResult addConsumer(@RequestBody Consumer consumer) {
        consumer.setTid(ManagerConfig.getTid());
        int i = consumerService.addConsumer(consumer);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 通过id删除当前数据
     */
    @PreAuthorize("@ss.hasPermi('system:game:delConsumer')")
    @ApiOperation(value = "删除客服信息")
    @Log(title = "删除客服信息", businessType = BusinessType.DELETE)
    @PostMapping("/delConsumer")
    public AjaxResult delConsumer(String id) {
        Integer i = consumerService.delConsumer(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 编辑
     * @param consumer 需要修改的内容
     */
    @PreAuthorize("@ss.hasPermi('system:game:editConsumer')")
    @ApiOperation(value = "编辑客服信息")
    @Log(title = "编辑客服信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editConsumer")
    public AjaxResult editConsumer(@RequestBody Consumer consumer) {
        int i = consumerService.editConsumer(consumer);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
