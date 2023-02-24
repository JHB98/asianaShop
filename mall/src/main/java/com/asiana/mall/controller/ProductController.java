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
    //    TODO: 삭제해야됨
    //    @GetMapping("/main") // 매핑값인 main로 view인 main.html을 유추한다
    //    public @ModelAttribute("Page") Page list(@RequestParam(required = false, defaultValue = "1") int curPage, Page page)
    //            throws Exception {
    //
    //        Page pageDTO = productService.getProductList(curPage, page); // Model
    //        return pageDTO; // Model
    //    }

    @GetMapping("/ShopMiniMall/main")
    public ModelAndView getMain(@RequestParam(required = false, defaultValue = "1") int curPage, Product product,
            ModelAndView mv, Cart cart, Page page) {
        mv.setViewName("/ShopMiniMall/main");
        mv.addObject("cart", cart);
        mv.addObject("Page", productService.getProductList(curPage, page));
        // return pageDTO; // Model;
        return mv;
    }

    @GetMapping("/ShopMiniMall/product/category/{category}/{curPage}")
    public ModelAndView getProductByCategory(ModelAndView mv, @PathVariable("category") String category, @PathVariable("curPage") int curPage,
                                             Cart cart, Page page) {
//        mv.setViewName("/ShopMiniMall/main :: resultProduct");
        mv.setViewName("/ShopMiniMall/category");
        mv.addObject("cart", cart);
        mv.addObject("Page", productService.getProductListByCategory(curPage, page, category));

        return mv;  
    }
 

    @GetMapping("/ShopMiniMall/product/{number}")
    public ModelAndView getProductById(@PathVariable("number") int number, ModelAndView mv, Cart cart, Product product) {
        mv.setViewName("/ShopMiniMall/product");

        product = productService.getProductById(number);
        
        cart.setProductNum(number);
        cart.setBrand(product.getBrand());
        cart.setName(product.getName());
        cart.setPrice(product.getPrice());
        cart.setCategory(product.getCategory());
        
        mv.addObject("cart", cart);
        mv.addObject("data", productService.getProductById(number));
        mv.addObject("product", product);

        return mv;
    }
}
