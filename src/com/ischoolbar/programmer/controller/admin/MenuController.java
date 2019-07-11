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
 * �˵����������
 * @author yangrundong
 *
 */

@RequestMapping("/menu")
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	
	
	
	
	/**
	 * �˵��б����
	 * @param model
	 * @return
	 */
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET )
	public ModelAndView list(ModelAndView model){
		model.setViewName("menu/list");
		return model;
	}
	
	/**
	 * ��ȡ�˵��б�
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
	 * �˵����
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Menu menu){
		Map<String, String> ret = new HashMap<String, String>();
		if (menu == null ) {
			ret.put("type","error");
			ret.put("msg","����д��ȷ�Ĳ˵���Ϣ");
			return ret;
		}
		if (StringUtils.isNullOrEmpty(menu.getName())) {
			ret.put("type","error");
			ret.put("msg","����д��ȷ�Ĳ˵�����");
			return ret;
			
		}
		if (StringUtils.isNullOrEmpty(menu.getIcon())) {
			ret.put("type","error");
			ret.put("msg","����д��ȷ�Ĳ˵�ͼ��");
			return ret;
			
		}
		if (menu.getParentId()==null) {
			menu.setParentId(-1l);
			
		}
		if (menuService.add(menu) <= 0) {
			ret.put("type","error");
			ret.put("msg","���ʧ�ܣ�����ϵ����Ա");
			return ret;
		}
		
		ret.put("type","success");
		ret.put("msg","��ӳɹ�");
		return ret;
	}
}
