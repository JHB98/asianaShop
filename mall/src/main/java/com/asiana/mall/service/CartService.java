package com.asiana.mall.service;

import java.util.List;
import com.asiana.mall.vo.Cart;

public interface CartService {
    List<Cart> getCart(String userId);

    void postCart(Cart cart);

    void deleteCart(int cartNum);

    void putCartAmount(int cartNum, int amount);
}
