package com.group2.theminimart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.group2.theminimart.dto.CartDto;
import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.exception.CartContentAlreadyExistException;
import com.group2.theminimart.exception.CartContentNotFoundException;
import com.group2.theminimart.exception.CartNotFoundException;
import com.group2.theminimart.exception.ProductNotFoundException;
import com.group2.theminimart.exception.UserNotFoundException;
import com.group2.theminimart.mapper.CartMapper;
import com.group2.theminimart.repository.CartContentRepository;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.UserRepository;


@Primary
@Service
public class CartContentServiceImpl implements CartContentService {

    private  ProductRepository productRepository;
    private CartContentRepository cartContentRepository;
    private UserRepository userRepository;

    public CartContentServiceImpl(CartContentRepository cartContentRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartContentRepository = cartContentRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartContent createCartContent(CartContent cartContent) {
        return cartContentRepository.save(cartContent);
    }

    @Override
    public CartDto createCartContent(Long userId, CartDto cartDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Product product = productRepository.findById(cartDto.getProductId()).orElseThrow(() -> new ProductNotFoundException(cartDto.getProductId()));
        if(cartContentRepository.findByUserIdAndProductId(userId, product.getId()).isPresent()) {
            throw new CartContentAlreadyExistException(userId, product.getId());
        }
        return CartMapper.toDto(cartContentRepository.save( CartMapper.fromDto(cartDto, user, product)));
    }   

    @Override
    public List<CartDto> getCartContents(Long userId) {
        List<CartContent> cart = 
            cartContentRepository
                .findByUserId(userId)
                .orElseThrow(() ->  new UserNotFoundException(userId));
        return cart
                .stream()
                .map(CartMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartDto getCartContent(Long userId) {
        return CartMapper.toDto(cartContentRepository.findById(userId).orElseThrow(() -> new CartNotFoundException(userId)));
    }

    @Override
    public List<CartDto> updateCart(Long userId, List<CartDto> cartDtoList) {
        List<CartContent> userCart = cartContentRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));

        List<CartContent> updatedCart = userCart
            .stream()
            .map(existingCartContent -> {
                CartDto cartDto = cartDtoList.stream()
                        .filter(dto -> dto.getProductId().equals(existingCartContent.getProduct().getId()))
                        .findFirst()
                        .orElseThrow(() -> new CartContentNotFoundException(userId, existingCartContent.getProduct().getId()));

                existingCartContent.setCount(cartDto.getCount());
                existingCartContent.setTotal(cartDto.getTotal());

                return cartContentRepository.save(existingCartContent);
            })
            .collect(Collectors.toList());

        return updatedCart
            .stream()
            .map(CartMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public CartDto updateCartContent(Long userId, CartDto cartDto) {
        CartContent exisitingCartContent = cartContentRepository
            .findByUserIdAndProductId(userId, cartDto.getProductId())
            .orElseThrow(() -> 
                new CartContentNotFoundException(userId, cartDto.getProductId()));
        exisitingCartContent.setCount(cartDto.getCount());
        exisitingCartContent.setTotal(cartDto.getTotal());
        return CartMapper.toDto(cartContentRepository.save(exisitingCartContent));
    }

    @Override
    public void deleteCart(Long userId) {
        List<CartContent> cart = cartContentRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));

        cartContentRepository.deleteAll(cart);
    }

    @Override
    public void deleteCartContent(Long userId, Long productId) {
        CartContent removeCartContent = cartContentRepository.findByUserIdAndProductId(userId, productId).orElseThrow(() -> new CartContentNotFoundException(userId, productId));
        cartContentRepository.delete(removeCartContent);
    }
}
