package cn.e3mall.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.service.ItemService;

@Controller
public class CartController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;

	// 添加购物车
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		// 判断是否已经登录
		TbUser user = (TbUser) request.getAttribute("user");
		// 如果处于登录状态，将数据写入redis中
		if (user != null) {
			cartService.addCart(user.getId(), itemId, num);
			return "cartSuccess";
		}
		// 如果未登录，将数据写入cookie中
		// 从cookie中查询商品
		List<TbItem> cartList = getCartListFromCookie(request);
		// 判断商品是否存在cookie中
		boolean flag = false;
		for (TbItem tbItem : cartList) {
			// 如果存在，修改商品数量
			if (tbItem.getId() == num.longValue()) {
				// 存在商品，数量相加
				tbItem.setNum(tbItem.getNum() + num);
				flag = true;
				// 跳出循环
				break;
			}
		}
		if (!flag) {
			// 如果不存在，根据商品id查询商品
			TbItem tbItem = itemService.findItemById(itemId);
			tbItem.setNum(num);
			// 取一张图片
			String image = tbItem.getImage();
			if (StringUtils.isNoneBlank(image)) {
				tbItem.setImage(image.split(",")[0]);
			}
			// 将商品添加到商品列表中
			cartList.add(tbItem);
		}
		// 写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), 7200, true);
		return "cartSuccess";
	}

	// 展示购物车
	@RequestMapping("/cart/cart")
	public String showCart(HttpServletRequest request, HttpServletResponse response) {
		// 未登录状态
		List<TbItem> cartList = getCartListFromCookie(request);
		//判断用户是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			// 登录状态
			// 从cookie中取购物车列表
			// 如果不为空，把cookie中的购物车商品和redis中的购物车商品合并
			cartService.mergeCart(user.getId(), cartList);
			// 把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			// 从redis中取购物车列表
			cartList = cartService.getCartList(user.getId());
		}
		request.setAttribute("cartList", cartList);
		return "cart";
	}

	// 修改购物车商品数量
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num, 
			HttpServletRequest request,HttpServletResponse response) {
		// 判断用户是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			cartService.updateCart(user.getId(), itemId, num);
			return E3Result.ok();
		}
		// 从cookie中取商品列表
		List<TbItem> cartList = getCartListFromCookie(request);
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 更新数量
				tbItem.setNum(num);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), 7200, true);
		return E3Result.ok();
	}

	// 删除购物车
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否登录
		TbUser user = (TbUser) request.getAttribute("user");
		if (user != null) {
			cartService.deleteCart(user.getId(), itemId);
			return "redirect:/cart/cart.html";
		}

		// 从cookie中取商品列表
		List<TbItem> cartList = getCartListFromCookie(request);
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				// 更新数量
				cartList.remove(tbItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), 7200, true);

		return "redirect:/cart/cart.html";
	}

	private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, "cart", true);
		// 判断json是否为空
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		// 把json转换为商品列表
		List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
		return list;

	}
}
