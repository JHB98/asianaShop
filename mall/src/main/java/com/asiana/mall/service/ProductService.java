package com.asiana.mall.service;

import java.util.List;
import com.asiana.mall.vo.Product;

public interface ProductService {

    List<Product> getProduct(Product product);

    Product getProductById(int number);

    List<Product> getProductByCategory(String category);

}
