package com.group2.theminimart.service;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.exception.CartNotFoundException;
import com.group2.theminimart.exception.UserNotFoundException;
import com.group2.theminimart.repository.CartContentRepository;
import com.group2.theminimart.repository.UserRepository;


@Primary
@Service
public class CartContentServiceImpl implements CartContentService {
    private CartContentRepository cartContentRepository;
    private UserRepository userRepository;

    public CartContentServiceImpl(CartContentRepository cartContentRepository, UserRepository userRepository) {
        this.cartContentRepository = cartContentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartContent createCartContent(CartContent cartContent) {
        return cartContentRepository.save(cartContent);
    }

    @Override
    public CartContent createCartContent(Long userId, CartContent cartContent) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        cartContent.setUser(user);
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
