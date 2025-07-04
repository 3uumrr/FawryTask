package com.spring.models.product;

import java.math.BigDecimal;

public class NonExpirableProduct extends Product {
    public NonExpirableProduct(String name, BigDecimal price, Integer quantity) {
        super(name, price, quantity);
    }
}
