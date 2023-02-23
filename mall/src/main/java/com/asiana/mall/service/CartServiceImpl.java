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

        List<Cart> cartList = cartMapper.selectCart(userId);
        for (Cart refCart : cartList) {
            refCart.setTotalPrice(refCart.getPrice() * refCart.getAmount());
        }
        return cartList;
    }

    @Override
    public void postCart(Cart cart) {
        cartMapper.insertCart(cart);
    }

    @Override
    public void deleteCart(int cartNum) {
        cartMapper.deleteCart(cartNum);
    }

    @Override
    public void putCartAmount(int cartNum, int amount) {
        cartMapper.updateCartAmount(cartNum, amount);
    }

    @Override
    public void deleteCartById(String userId) {
        cartMapper.deleteCartById(userId);
    }
}
