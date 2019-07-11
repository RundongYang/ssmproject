package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Menu;

/**
 * ≤Àµ•π‹¿Ìservices
 * @author llq
 *
 */
@Service
public interface MenuService {
	public int add(Menu menu);
	public List<Menu> findList(Map<String, Object> queryMap);
	


}
