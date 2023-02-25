package com.asiana.mall.service;

import java.util.List;
import com.asiana.mall.vo.Cart;

public interface CartService {
    List<Cart> getCart(String userId);

    void postCart(Cart cart);

    void deleteCart(int cartNum);

    void deleteCartById(String userId);

    void putCartAmount(int cartNum, int amount);

    void deleteCartByNumberList(String cartNumList);
}
