package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MenuDao;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.service.admin.MenuService;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	
	
	private  MenuDao menuDao;
	
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.add(menu);
	}

	@Override
	public List<Menu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.findList(queryMap);
	}




}
