package com.spring;

import com.spring.models.cart.Cart;
import com.spring.models.customer.Customer;
import com.spring.models.product.*;
import com.spring.services.CheckoutService;
import java.math.BigDecimal;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        Product cheese = new ShippableAndExpirableProduct("Cheese",new BigDecimal(100),4,LocalDate.of(2025, 7, 10),200);
        Product biscuits = new ShippableAndExpirableProduct("Biscuits",new BigDecimal(150),4,LocalDate.of(2025, 7, 10),700);
        Product scratchCard = new NonExpirableProduct("scratchCard",new BigDecimal(20),3);

        Cart cart = new Cart();
        Customer customer = new Customer("Omar",10000, cart);

        cart.add(cheese,2);
        cart.add(biscuits,1);
        cart.add(scratchCard,10);

        try {
            CheckoutService.checkout(customer,cart);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}