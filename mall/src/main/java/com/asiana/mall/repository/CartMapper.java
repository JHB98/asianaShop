package com.asiana.mall.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.asiana.mall.vo.Cart;

@Mapper
public interface CartMapper {
    List<Cart> selectCart(@Param("userId") String userId);

    void insertCart(@Param("cart") Cart cart);
}
