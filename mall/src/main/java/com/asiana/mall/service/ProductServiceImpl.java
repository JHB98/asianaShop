package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Page;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiana.mall.repository.ProductMapper;
import com.asiana.mall.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SqlSessionTemplate session;

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

    @Override
    public List<Product> getProductByCategory(String category) {
        return productMapper.selectProductListByCategory(category);
    }

    @Override
    public Page getProductList(int curPage, Page page) {
        // curPage 저장

        // totalPage 저장
        int perPage = page.getPerPage(); // 4
        int totalRecord = productMapper.selectTotalRecord();
        int totalPage = totalRecord / perPage;
        if (totalRecord % perPage != 0)
            totalPage++;

        // List<Product> 저장
        int offset = (curPage - 1) * perPage;
        List<Product> list = session.selectList("com.asiana.mall.repository.ProductMapper.selectProductList", null,
                new RowBounds(offset, perPage));

        // pageDTO 최종저장
        page.setCurPage(curPage);
        page.setList(list);
        page.setTotalPage(totalPage);

        return page;
    }

    @Override
    public Page getProductListByCategory(int curPage, Page page, String category) {
        // curPage 저장

        // totalPage 저장
        int perPage = page.getPerPage(); // 4
        int totalRecord = productMapper.selectTotalRecordByCategory(category);
        int totalPage = totalRecord / perPage;
        if (totalRecord % perPage != 0)
            totalPage++;

        // List<Product> 저장
        int offset = (curPage - 1) * perPage;
        List<Product> list = session.selectList("com.asiana.mall.repository.ProductMapper.selectProductListByCategory", category,
                new RowBounds(offset, perPage));

        // pageDTO 최종저장
        page.setCurPage(curPage);
        page.setList(list);
        page.setTotalPage(totalPage);

        return page;
    }
}
