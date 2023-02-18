package com.asiana.mall.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.asiana.mall.repository.ProductMapper;
import com.asiana.mall.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getProduct(Product product) {
        return productMapper.selectProduct(product);
    }

    @Override
    public Product getProductById(int number) {
        return productMapper.selectProductById(number);
    }
}
