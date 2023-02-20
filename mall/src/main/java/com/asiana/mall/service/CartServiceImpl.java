package com.asiana.mall.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.asiana.mall.repository.CartMapper;
import com.asiana.mall.vo.Cart;

@Service
public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;

    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public List<Cart> getCart(String userId) {
        return cartMapper.selectCart(userId);
    }

    @Override
    public void postCart(Cart cart) {
        cartMapper.insertCart(cart);
    }

    @Override
    public void deleteCart(int cartNum) {
        cartMapper.deleteCart(cartNum);
    }
}
