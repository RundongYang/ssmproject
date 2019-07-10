package com.ischoolbar.programmer.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.UserDao;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.UserService;


/**
 * user用户serviceimpl
 * @author yangrundong
 *
 */
@Service
public class UserServiceImpl implements UserService {
	//可以自动的从容器中拿出数据
	@Autowired
	private UserDao userDao;
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

}
