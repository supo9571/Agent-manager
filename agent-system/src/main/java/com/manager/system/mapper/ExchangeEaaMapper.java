package com.manager.system.mapper;

import com.manager.common.core.domain.entity.ExchangeEaa;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 提现审批
 * @author sieGuang 2021/09/16
 */
@Mapper
public interface ExchangeEaaMapper {

    /**
     * 查询
     */
    List<ExchangeEaa> getExchangeEaaList(ExchangeEaa exchangeEaa);

    /**
     * 玩家打码信息查询
     */
    List<Map> getAddMosaicPlayerList(@Param("uid") int uid);

    int editExchangeEaaList(ExchangeEaa exchangeEaa);

    List<Map> getTransferMode();

}
