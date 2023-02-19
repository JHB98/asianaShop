package com.asiana.mall.service;

import java.util.List;
import com.asiana.mall.vo.Cart;

public interface CartService {
    List<Cart> getCart(String userId);
}
