package com.spring.models.cart;

import com.spring.models.product.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class Cart {
    private Set<CartItem> cartItems = new HashSet<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;


    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalAmount() {
        setTotalAmount();
        return totalAmount;
    }

    public void setTotalAmount() {
        totalAmount = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public void add(Product product , int quantity){
        CartItem cartItem = new CartItem(product,quantity);
        cartItems.add(cartItem);
        setTotalAmount();
    }

}
