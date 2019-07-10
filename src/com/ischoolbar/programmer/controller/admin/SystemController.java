package com.ischoolbar.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.UserService;
import com.ischoolbar.programmer.util.CpachaUtil;
import com.mysql.jdbc.StringUtils;



@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired

	private UserService userService;

	
	
	/**
	 * 系统登录后的主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index", method=RequestMethod.GET  )
	
	
	public ModelAndView index(ModelAndView model){
		
		model.setViewName("system/index");
		return model;
	}
	
	/**
	 * 系统登录后欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/welcome", method=RequestMethod.GET  )
	
	public ModelAndView welcome(ModelAndView model){
		
		model.setViewName("system/welcome");
		return model;
	}
	
	/**
	 * 登录界面
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/login", method= RequestMethod.GET )
	
	public ModelAndView login(ModelAndView model){
		
		model.setViewName("system/login");
		return model;
	}

	@RequestMapping(value="/login", method= RequestMethod.POST )
	@ResponseBody
	public Map<String, String> loginAct(User user, String cpacha,HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
	
		if (user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息");
			return ret;
			
		}
		if (StringUtils.isNullOrEmpty(cpacha)) {
			ret.put("type", "error");
			ret.put("msg", "请填写验证码");
			return ret;
			
		}
		if (StringUtils.isNullOrEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名");
			return ret;			
		}
		if (StringUtils.isNullOrEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码");
			return ret;
			
		}
		
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		
		if (loginCpacha== null) {	
			ret.put("type", "error");
			ret.put("msg", "回话超时asd，请刷新网页");
			return ret;
			
		}
		if (!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
			
			ret.put("type", "error");
			ret.put("msg", "验证码错误");
			return ret;
		}
		
		User findByUsername = userService.findByUsername(user.getUsername());
		if (findByUsername == null) {
			ret.put("type", "error");
			ret.put("msg", "该用户名不存在");
			return ret;
		}
		
		if (!user.getPassword().equals(findByUsername.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码错误");
			return ret;
		}
		request.getSession().setAttribute("admin", findByUsername);
		
		ret.put("type", "success");
		ret.put("msg", "登录成功");
		return ret;
	}

	
	
	
	/**
	 * 本系统所有验证码都使用这个防范
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType ：用来区别验证码的类型
	 * @param request
	 * @param response
	 */
	@RequestMapping(value ="/get_Cpacha",method =RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",required = false,defaultValue ="4") Integer vcodeLen,
			@RequestParam(name="w",required = false,defaultValue ="100") Integer width,
			@RequestParam(name="h",required = false,defaultValue ="30") Integer height,
			@RequestParam(name="type",required = true,defaultValue ="loginCpacha") String cpachaType,

			HttpServletRequest request ,
			HttpServletResponse response){
		
		
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode =cpachaUtil.generatorVCode();
		
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};	
}
