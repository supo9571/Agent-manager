package com.manager.system.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.annotation.DataSource;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.entity.ExchangeEaa;
import com.manager.common.core.domain.entity.Pay;
import com.manager.common.core.domain.entity.RechargeOrder;
import com.manager.common.enums.DataSourceType;
import com.manager.system.mapper.ExchangeEaaMapper;
import com.manager.system.service.ExchangeEaaService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提现审批
 * @author sieGuang 2021/09/16
 */
@Service
@DataSource(DataSourceType.SLAVE)
public class ExchangeEaaServiceImpl implements ExchangeEaaService {

    @Autowired
    private ExchangeEaaMapper exchangeEaaMapper;

    @Override
    public Map getExchangeEaaList(ExchangeEaa exchangeEaa) {

        // 放回参数
        Map result = new HashMap();
        // 审核状态  1待审批 2待打款 3已打款 4打款中  5打款失败 6已没收 7已退款
        int exchangeNum1 = 0;
        int exchangeNum2 = 0;
        int exchangeNum3 = 0;
        int exchangeNum4 = 0;
        int exchangeNum5 = 0;
        int exchangeNum6 = 0;
        int exchangeNum7 = 0;
        // 审核状态  1待审批 2待打款 3已打款 32实际打款 33手续费 4打款中  5打款失败 6已没收 7已退款
        BigDecimal exchangeMoney1 = new BigDecimal(0);
        BigDecimal exchangeMoney2 = new BigDecimal(0);
        BigDecimal exchangeMoney3 = new BigDecimal(0);
        BigDecimal exchangeMoney32 = new BigDecimal(0);
        BigDecimal exchangeMoney33 = new BigDecimal(0);
        BigDecimal exchangeMoney4 = new BigDecimal(0);
        BigDecimal exchangeMoney5 = new BigDecimal(0);
        BigDecimal exchangeMoney6 = new BigDecimal(0);
        BigDecimal exchangeMoney7 = new BigDecimal(0);

        // 设置分页数据
        PageHelper.startPage(exchangeEaa.getPage(), exchangeEaa.getSize(),
                exchangeEaa.getOrderByColumn()+" "+exchangeEaa.getIsAsc());

        List<ExchangeEaa> list = exchangeEaaMapper.getExchangeEaaList(exchangeEaa);

        PageInfo<ExchangeEaa> pageInfo = new PageInfo<ExchangeEaa>(list);
        result.put("page",exchangeEaa.getPage());
        result.put("size",exchangeEaa.getSize());
        result.put("total",pageInfo.getTotal());

        // 只处理分页的数据
        list = pageInfo.getList();

        // 左下角的哪几个字段
        if(CollectionUtils.isNotEmpty(list)){

            for (ExchangeEaa eaa : list) {
                if("1".equals(eaa.getExaaStatus())){
                    exchangeNum1++;
                    exchangeMoney1.add(eaa.getTransferAmount());
                }else if("2".equals(eaa.getExaaStatus())){
                    exchangeNum2++;
                    exchangeMoney2.add(eaa.getTransferAmount());
                }else if("3".equals(eaa.getExaaStatus())){
                    exchangeNum3++;
                    exchangeMoney3.add(eaa.getWithdrawMoney());
                    exchangeMoney32.add(eaa.getTransferAmount());
                    exchangeMoney33.add(eaa.getPoundage());
                }else if("4".equals(eaa.getExaaStatus())){
                    exchangeNum4++;
                    exchangeMoney4.add(eaa.getTransferAmount());
                }else if("5".equals(eaa.getExaaStatus())){
                    exchangeNum5++;
                    exchangeMoney5.add(eaa.getTransferAmount());
                }else if("6".equals(eaa.getExaaStatus())){
                    exchangeNum6++;
                    exchangeMoney6.add(eaa.getTransferAmount());
                }else if("7".equals(eaa.getExaaStatus())){
                    exchangeNum7++;
                    exchangeMoney7.add(eaa.getTransferAmount());
                }
            }
        }

        result.put("exchangeNum1",exchangeNum1);
        result.put("exchangeNum2",exchangeNum2);
        result.put("exchangeNum3",exchangeNum3);
        result.put("exchangeNum4",exchangeNum4);
        result.put("exchangeNum5",exchangeNum5);
        result.put("exchangeNum6",exchangeNum6);
        result.put("exchangeNum7",exchangeNum7);

        result.put("exchangeMoney1",exchangeMoney1);
        result.put("exchangeMoney2",exchangeMoney2);
        result.put("exchangeMoney3",exchangeMoney3);
        result.put("exchangeMoney32",exchangeMoney32);
        result.put("exchangeMoney33",exchangeMoney33);
        result.put("exchangeMoney4",exchangeMoney4);
        result.put("exchangeMoney5",exchangeMoney5);
        result.put("exchangeMoney6",exchangeMoney6);
        result.put("exchangeMoney7",exchangeMoney7);

        result.put("rows",list);
        return result;
    }

    @Override
    public List getAddMosaicPlayerList(String uid,String tid) {
        List list = exchangeEaaMapper.getAddMosaicPlayerList(uid,tid);
        return list;
    }

    @Override
    public List export(ExchangeEaa exchangeEaa) {
        List list = exchangeEaaMapper.getExchangeEaaList(exchangeEaa);
        return list;
    }

    @Override
    public int editExchangeEaaList(ExchangeEaa exchangeEaa) {
        return exchangeEaaMapper.editExchangeEaaList(exchangeEaa);
    }

}
