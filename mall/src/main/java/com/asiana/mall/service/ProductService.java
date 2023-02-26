package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Cart;
import com.asiana.mall.vo.Page;
import com.asiana.mall.vo.Product;
import com.asiana.mall.vo.Purchase;

public interface ProductService {

    List<Product> getProduct(Product product);

    Product getProductById(int number);

    List<Product> getProductByCategory(String category);

    void putProductCount(Cart cart, Product product);

    Page getProductList(int curPage, Page page);

    Page getProductListByCategory(int curPage, Page page, String category);

    void putProductAmount(List<Purchase> purchase);
}
