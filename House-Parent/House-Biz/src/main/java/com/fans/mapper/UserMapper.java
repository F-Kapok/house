package com.fans.mapper;

import com.fans.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByEmail(String value);

    int updateByEmailSelective(User user);

    List<User> query(User user);

    int update(User updateUser);
}