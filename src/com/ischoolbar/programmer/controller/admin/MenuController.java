package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Menu;
import com.mysql.jdbc.StringUtils;

/**
 * �˵����������
 * @author yangrundong
 *
 */

@RequestMapping("/menu")
@Controller
public class MenuController {
	
	
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
		ret.put("type","success");
		ret.put("msg","��ӳɹ�");
		return ret;
	}
}
