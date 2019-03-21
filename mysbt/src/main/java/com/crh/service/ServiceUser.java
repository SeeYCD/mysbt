package com.crh.service;

import org.springframework.transaction.annotation.Transactional;

import com.crh.entity.User;
public interface ServiceUser {
	
	public User selectByPrimaryKey(Integer id);
	public int insert(User record);
	public int updateByPrimaryKeySelective(User record);

}
