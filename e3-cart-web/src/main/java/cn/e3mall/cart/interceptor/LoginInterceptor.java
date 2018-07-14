package cn.e3mall.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 前处理，执行handler前执行此方法 返回true放行 false拦截
		//1.从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		//2.如果没有token 则未登录，直接放行
		if(StringUtils.isBlank(token)) {
			return true;
		}
		//3.取到token，调用sso系统服务，根据token取用户信息
		E3Result result = tokenService.getUserByToken(token);
		//4.没有取到用户信息，登录过期，直接放行
		if(result.getStatus()!=200) {
			return true;
		}
		//5.取到用户信息，登录状态
		TbUser user = (TbUser) result.getData();
		//6.把用户信息放到request中。在controller中判断request中师傅包含user信息 
		request.setAttribute("user", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub

	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
