package com.fans.mapper;

import com.fans.model.Community;

import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<Community> selectCommunity(Community community);
}