package com.asiana.mall.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.asiana.mall.vo.Product;

@Mapper
public interface ProductMapper {
    List<Product> selectProduct(Product product);

    Product selectProductById(@Param("number") int number);

    int selectTotalRecord();

    List<Product> selectProductList();

    void updateProductCount(@Param("productNum") int productNum, @Param("totalCount") int totalCount);
    int selectTotalRecordByCategory(@Param("category") String category);

    List<Product> selectProductListByCategory(@Param("category") String category);
}
