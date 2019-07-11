package com.ischoolbar.programmer.dao.admin;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Menu;


@Repository
public interface MenuDao {

	public int  add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
}
