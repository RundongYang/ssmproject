package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.MenuService;
import com.mysql.jdbc.StringUtils;

/**
 * 菜单管理控制器
 * @author yangrundong
 *
 */

@RequestMapping("/menu")
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	
	
	
	
	/**
	 * 菜单列表管理
	 * @param model
	 * @return
	 */
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET )
	public ModelAndView list(ModelAndView model){
		model.setViewName("menu/list");
		return model;
	}
	
	/**
	 * 获取菜单列表
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getMenuList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("name", name);
		List<Menu> findList = menuService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", 10);
		return ret;
	}
	/**
	 * 菜单添加
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Menu menu){
		Map<String, String> ret = new HashMap<String, String>();
		if (menu == null ) {
			ret.put("type","error");
			ret.put("msg","请填写正确的菜单信息");
			return ret;
		}
		if (StringUtils.isNullOrEmpty(menu.getName())) {
			ret.put("type","error");
			ret.put("msg","请填写正确的菜单名称");
			return ret;
			
		}
		if (StringUtils.isNullOrEmpty(menu.getIcon())) {
			ret.put("type","error");
			ret.put("msg","请填写正确的菜单图标");
			return ret;
			
		}
		if (menu.getParentId()==null) {
			menu.setParentId(-1l);
			
		}
		if (menuService.add(menu) <= 0) {
			ret.put("type","error");
			ret.put("msg","添加失败，请联系管理员");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg","添加成功");
		return ret;
	}
}
