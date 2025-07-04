package com.spring.models.product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShippableAndExpirableProduct extends ExpirableProduct implements Shippable {
    private double weight;
    public ShippableAndExpirableProduct(String name, BigDecimal price, Integer quantity, LocalDate expireDate , double weight) {
        super(name, price, quantity, expireDate);
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
