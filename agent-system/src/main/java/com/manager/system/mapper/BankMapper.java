package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/11
 */
@Mapper
public interface BankMapper {

    @Select("select * from config_bank")
    List<Map> getBankList();
}
