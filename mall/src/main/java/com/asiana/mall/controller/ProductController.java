package com.asiana.mall.controller;

import com.asiana.mall.service.ProductService;
import com.asiana.mall.vo.Cart;
import com.asiana.mall.vo.InfoList;
import com.asiana.mall.vo.Page;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/ShopMiniMall/main")
    public ModelAndView getMain(@RequestParam(required = false, defaultValue = "1") int curPage, Product product,
            ModelAndView mv, Cart cart, Page page) {
        mv.setViewName("/ShopMiniMall/main");
        mv.addObject("cart", cart);
        mv.addObject("Page", productService.getProductList(curPage, page));

        return mv;
    }

    @GetMapping("/ShopMiniMall/product/category/{category}/{curPage}")
    public ModelAndView getProductByCategory(ModelAndView mv, @PathVariable("category") String category,
            @PathVariable("curPage") int curPage,
            Cart cart, Page page) {
        mv.setViewName("/ShopMiniMall/category");
        mv.addObject("cart", cart);
        mv.addObject("Page", productService.getProductListByCategory(curPage, page, category));

        return mv;
    }

    @GetMapping("/ShopMiniMall/product/{number}")
    public ModelAndView getProductById(@PathVariable("number") int number, ModelAndView mv, Cart cart, InfoList list) {
        mv.setViewName("/ShopMiniMall/product");
        mv.addObject("cart", cart);
        mv.addObject("data", productService.getProductById(number));
        mv.addObject("list", list);

        return mv;
    }

    @PutMapping("/ShopMiniMall/product/amount")
    public void putProductAmount(@RequestBody List<Purchase> list) {
        productService.putProductAmount(list);
    }
}
