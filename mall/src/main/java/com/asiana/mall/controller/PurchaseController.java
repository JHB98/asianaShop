package com.asiana.mall.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.PurchaseServiceImpl;
import com.asiana.mall.vo.Product;

@Controller
public class PurchaseController {
	private PurchaseServiceImpl purchaseService;

	public PurchaseController(PurchaseServiceImpl purchaseService) {
		this.purchaseService = purchaseService;
	}

	@GetMapping("/ShopMiniMall/purchase")
	public ModelAndView getPurchase(HttpServletRequest request, ModelAndView mv) throws Exception {

		String[] itemNumbers = request.getParameterValues("number");

		Integer[] itemNumbersInt = Stream.of(itemNumbers).mapToInt(Integer::parseInt)
				.boxed().toArray(Integer[]::new);

		List<Integer> list = Arrays.asList(itemNumbersInt);

		List<Product> productList = purchaseService.selectProductListByIdList(list);
		mv.setViewName("/ShopMiniMall/purchase");
		mv.addObject("purchaseProductsList", productList);
		return mv;
	}

}
