package com.asiana.mall.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.CartServiceImpl;
import com.asiana.mall.service.ProductServiceImpl;
import com.asiana.mall.service.PurchaseServiceImpl;
import com.asiana.mall.vo.Cart;
import com.asiana.mall.vo.InfoList;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

@Controller
public class PurchaseController {
	private PurchaseServiceImpl purchaseService;
	private ProductServiceImpl productService;
	private final CartServiceImpl cartService;

	public PurchaseController(PurchaseServiceImpl purchaseService, ProductServiceImpl productService,
			CartServiceImpl cartService) {
		this.purchaseService = purchaseService;
		this.productService = productService;
		this.cartService = cartService;
	}

	@GetMapping("/ShopMiniMall/purchase") // 바로구매
	public ModelAndView getPurchase(ModelAndView mv, @AuthenticationPrincipal User userInfo, InfoList list,
			Product product, Cart cart) throws Exception {
		int productNum = Integer.parseInt(list.getList()); // 상품코드 상품 명 수량 주문 금액
		product = productService.getProductById(productNum);

		cart.setCartNum(0);
		cart.setUserId(userInfo.getUsername());
		cart.setProductNum(productNum);
		cart.setBrand(product.getBrand());
		cart.setName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setAmount(1);
		cart.setTotalPrice(cart.getPrice() * cart.getAmount());

		mv.setViewName("/ShopMiniMall/purchase");
		mv.addObject("data", cart);
		mv.addObject("check", "Object");

		return mv;
	}

	@PostMapping("/ShopMiniMall/purchase")
	public void postPurchase(ModelAndView mv, @RequestBody List<Purchase> list) throws Exception {
		purchaseService.postPurchase(list);
	}

	@GetMapping("/ShopMiniMall/purchase/list")
	public ModelAndView getPurchaseList(ModelAndView mv, @AuthenticationPrincipal User userInfo, InfoList list)
			throws Exception {
		if (list.getList() == null) { // 전체구매
			mv.addObject("data", cartService.getCart(userInfo.getUsername()));
		} else { // 선택구매
			mv.setViewName("/ShopMiniMall/purchase");
			mv.addObject("data", cartService.getCartByNumberList(list.getList()));
		}

		mv.setViewName("/ShopMiniMall/purchase");
		mv.addObject("check", "List");
		return mv;
	}
}
