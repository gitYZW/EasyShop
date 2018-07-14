package cn.e3mall.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private TokenService tokenService;
	@Autowired
	private CartService cartService;
	@Value("${SSO_URL}")
	private String SSO_URL;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		//判断token是否存在
		if(StringUtils.isBlank(token)) {
			//如果token不存在 跳转到sso的登录页面。登录成功后重新跳转到请求的url
			response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
			return false;
		}
		//如果token存在 调用sso系统的服务 根据token获取用户信息
		E3Result e3result = tokenService.getUserByToken(token);
		//如果取不到 登录过期
		if(e3result.getStatus()!=200) {
			//如果token不存在 跳转到sso的登录页面。登录成功后重新跳转到请求的url
			response.sendRedirect(SSO_URL+"/page/login?redirect="+request.getRequestURL());
			return false;
		}
		//取到用户信息 存入request中
		TbUser user = (TbUser) e3result.getData();
		request.setAttribute("user", user);
		//判断cookie中是否有购物车数据，如果有就合并
		String json = CookieUtils.getCookieValue(request, "cart", true);
		if(StringUtils.isNoneBlank(json)) {
			cartService.mergeCart(user.getId(), JsonUtils.jsonToList(json, TbItem.class));
		}
		//放行
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}


}
