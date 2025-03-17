package com.group2.theminimart.service;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.exception.CartNotFoundException;
import com.group2.theminimart.repository.CartContentRepository;



@Primary
@Service
public class CartContentServiceImpl implements CartContentService {
    private CartContentRepository cartContentRepository;

    public CartContentServiceImpl(CartContentRepository cartContentRepository) {
        this.cartContentRepository = cartContentRepository;
    }

    @Override
    public CartContent createCartContent(CartContent cartContent) {
        return cartContentRepository.save(cartContent);
    }

    @Override
    public ArrayList<CartContent> getCartContents() {
        return (ArrayList<CartContent>) cartContentRepository.findAll();
    }

    @Override
    public CartContent getCartContent(Long id) {
        return cartContentRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    public CartContent updateCartContent(Long id, CartContent cartContent) {
        CartContent updatedCartContent = cartContentRepository.findById(id).orElseThrow(() ->  new CartNotFoundException(id));
        updatedCartContent.setCount(cartContent.getCount());
        updatedCartContent.setTotal(cartContent.getTotal());
        return cartContentRepository.save(updatedCartContent);
    }

    @Override
    public void deleteCartContent(Long id) {
        cartContentRepository.deleteById(id);
    }
}
