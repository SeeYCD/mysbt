package com.crh.dao;

import java.util.List;

import com.crh.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int insertBatch(List<User> list);
    
    int updateUserByList(List<User> list);

}