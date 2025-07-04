package com.spring.models.customer;

import com.spring.models.cart.Cart;

public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance, Cart cart) {
        this.name = name;
        this.balance = balance;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
