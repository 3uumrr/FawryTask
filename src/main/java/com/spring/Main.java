package com.spring;

import com.spring.models.cart.Cart;
import com.spring.models.cart.CartItem;
import com.spring.models.customer.Customer;
import com.spring.models.product.*;
import com.spring.services.CheckoutService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ShippableAndExpirableProduct("Cheese",new BigDecimal(100),4,LocalDate.of(2025, 7, 10),200);
        Product Biscuits = new ShippableAndExpirableProduct("Biscuits",new BigDecimal(150),4,LocalDate.of(2025, 7, 10),700);

        CartItem cartItem1 = new CartItem(cheese,2);
        CartItem cartItem2 = new CartItem(Biscuits,1);

        Cart cart = new Cart(Set.of(cartItem1,cartItem2));

        Customer customer = new Customer("Omar",10000, cart);

        CheckoutService.checkout(customer,cart);



    }
}