package com.asiana.mall.controller;

import com.asiana.mall.service.ProductService;
import com.asiana.mall.vo.Cart;
import com.asiana.mall.vo.Page;
import com.asiana.mall.vo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/main") // 매핑값인 main로 view인 main.html을 유추한다
    public @ModelAttribute("Page") Page list(@RequestParam(required=false, defaultValue = "1") int curPage) throws Exception{

        Page pageDTO = productService.productList(curPage); // Model
        return pageDTO; // Model
    }

    @GetMapping("/ShopMiniMall/main")
    public ModelAndView getMain(@RequestParam(required=false, defaultValue = "1") int curPage, Product product, ModelAndView mv, Cart cart) {
        mv.setViewName("/ShopMiniMall/main");
        mv.addObject("cart", cart);
        mv.addObject("Page", productService.productList(curPage));
//        return pageDTO; // Model;
        return mv;
    }
//    @GetMapping("/ShopMiniMall/main")
//    public ModelAndView getMain(Product product, ModelAndView mv, Cart cart) {
//        mv.setViewName("/ShopMiniMall/main");
//        mv.addObject("cart", cart);
//        mv.addObject("data", productService.getProduct(product));
//        return mv;
//    }
    @GetMapping("/ShopMiniMall/product/{number}")
	public ModelAndView getProductById(@PathVariable("number") int number, ModelAndView mv, Cart cart) {
		mv.setViewName("/ShopMiniMall/product");
		mv.addObject("cart", cart);
		mv.addObject("data", productService.getProductById(number));

		return mv;
	}
}
