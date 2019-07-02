package com.fans.mapper;

import com.fans.model.Agency;
import com.fans.model.User;
import com.fans.page.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Agency record);

    int insertSelective(Agency record);

    Agency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Agency record);

    int updateByPrimaryKey(Agency record);

    List<User> selectAgency(@Param("user") User user, @Param("pageParams") PageParams pageParams);

    List<Agency> select(Agency agency);

    Long selectAgentCount(@Param("user") User user);

}