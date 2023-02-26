package com.asiana.mall.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.asiana.mall.vo.Cart;

@Mapper
public interface CartMapper {
    List<Cart> selectCart(@Param("userId") String userId);

    void insertCart(@Param("cart") Cart cart);

    void deleteCart(@Param("cartNum") int cartNum);

    void updateCartAmount(@Param("cartNum") int cartNum, @Param("amount") int amount);

    void deleteCartById(@Param("userId") String userId);

    void deleteCartByNumberList(@Param("cartNumList") String cartNumList);

    List<Cart> selectCartByNumberList(@Param("cartNumList") String cartNumList);
}
