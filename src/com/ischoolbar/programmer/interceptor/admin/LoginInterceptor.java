package com.ischoolbar.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * ��̨��¼������
 * @author yangrundong
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		Object admin = request.getSession().getAttribute("admin");
		if (admin == null) {
			
			//��ʾ��¼�ɹ�����ʧ��
			System.out.println("����"+requestURI+"������������");
			String header = request.getHeader("X-Requested-With");
			if ("XMLHttpRequest".equals(header)) {
				//��ʾ��ajax����
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��ʱ�Ự��ʱ���߻�δ��¼�������µ�¼");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		
		return true;
	}

}
