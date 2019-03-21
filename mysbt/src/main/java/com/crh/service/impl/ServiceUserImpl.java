package com.crh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crh.dao.UserMapper;
import com.crh.entity.User;
import com.crh.service.ServiceUser;

@Service
public class ServiceUserImpl implements ServiceUser{
	@Autowired
	UserMapper userDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public User selectByPrimaryKey(Integer id) {
  		return userDao.selectByPrimaryKey(id);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int insert(User record) {
 		return userDao.insert(record);
	}
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
 	@Override
	public int updateByPrimaryKeySelective(User record) {
 		return userDao.updateByPrimaryKeySelective(record);
	}

}
