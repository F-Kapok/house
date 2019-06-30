package com.fans.mapper;

import com.fans.model.House;
import com.fans.model.HouseUser;
import com.fans.model.UserMsg;
import com.fans.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> selectPageHouse(@Param(value = "house") House house, @Param(value = "pageParams") PageParams pageParams);

    Long selectPageCount(@Param("house") House house);

    int insertUserMsg(@Param("userMsg") UserMsg userMsg);

    HouseUser selectSaleHouseUser(Long id);
}