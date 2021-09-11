package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Exchange;
import com.manager.common.core.domain.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 提现信息管理
 * @author sieGuang 2021/09/07
 */
@Mapper
public interface ExchangeMapper {

    /**
     * 查询
     */
    List<Map> getExchangeList(@Param("tid") Integer tid);

    /**
     * 编辑
     * @param exchange 需要修改的内容
     */
    int editExchange(Exchange exchange);

    /**
     * 编辑
     * @param settingsType 基础类型 1保留金额  2打码倍数 3提现总次数
     * @param value 值
     */
    int editSettingsExchange(@Param("settingsType") int settingsType,
                             @Param("value") String value,
                             @Param("tid") Integer tid);

}
