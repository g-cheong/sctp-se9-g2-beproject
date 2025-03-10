package com.group2.theminimart.carts;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public ArrayList<Cart> getCarts() {
        return (ArrayList<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart getCart( Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        Cart updatedCart = cartRepository.findById(id).get();
        updatedCart.setCount(cart.getCount());
        updatedCart.setTotal(cart.getTotal());
        return cartRepository.save(updatedCart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
