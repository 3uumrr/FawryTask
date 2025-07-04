package com.spring.models.cart;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class Cart {
    private Set<CartItem> cartItems = new HashSet<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;

    public Cart(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
        setTotalAmount();
    }

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

}
