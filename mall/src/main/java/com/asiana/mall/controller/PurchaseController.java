package com.asiana.mall.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.asiana.mall.service.PurchaseServiceImpl;
import com.asiana.mall.vo.Flight;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.PurchaseInfo;

@Controller
public class PurchaseController {
	private PurchaseServiceImpl purchaseService;
	public PurchaseController(PurchaseServiceImpl purchaseService) {
		this.purchaseService = purchaseService;
	}
		

	@GetMapping("/ShopMiniMall/purchase")
	public ModelAndView getPurchase(Product product, HttpServletRequest request, ModelAndView mv) throws Exception {
//		String [] itemNumbers= request.getParameterValues("productNum");
//		for (String i:itemNumbers) {
//			System.out.println(i);
//		}
//		System.out.println(itemNumbers);

//		Integer i = Integer.valueOf(product.getNumber())
//		Integer[] itemNumbersInt = [i];

		String [] itemNumbers = new String[] {Integer.toString(product.getNumber())};
		Integer[] itemNumbersInt = Stream.of(itemNumbers).mapToInt(Integer::parseInt)
				.boxed().toArray(Integer[]::new);
	
		
		List<Integer> list = Arrays.asList(itemNumbersInt);

		List<Product> productList = purchaseService.selectProductListByIdList(list);
		System.out.println(productList);
		mv.setViewName("/ShopMiniMall/purchase");
		mv.addObject("purchaseProductsList", productList);
		return mv;
	}	
}

