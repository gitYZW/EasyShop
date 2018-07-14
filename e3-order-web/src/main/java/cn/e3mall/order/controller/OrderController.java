package cn.e3mall.order.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.order.pojo.OrderInfo;
import cn.e3mall.order.service.OrderService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request) {
		//取用户id
		TbUser user = (TbUser) request.getAttribute("user");
		
		List<TbItem> cartList = cartService.getCartList(user.getId());
		request.setAttribute("cartList", cartList);
		return "order-cart";
	}
	
	@RequestMapping(value="/order/create",method=RequestMethod.POST)
	public String createCart(OrderInfo orderInfo,HttpServletRequest request) {
		//取用户信息
		TbUser user = (TbUser) request.getAttribute("user");
		//把用户信息添加到orderinfo中
		orderInfo.setUserId(user.getId());
		orderInfo.setBuyerNick(user.getUsername());
		//调用服务生产订单
		E3Result result = orderService.createOrder(orderInfo);
		if(result.getStatus()==200) {
			//清空购物车
			cartService.clearCart(user.getId());
		}
		//把订单号传递给页面
		request.setAttribute("orderId", orderInfo.getOrderId());
		request.setAttribute("payment", orderInfo.getPayment());
		return "success";
	}
}
