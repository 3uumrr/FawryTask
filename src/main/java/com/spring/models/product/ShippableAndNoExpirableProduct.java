package com.spring.models.product;

import java.math.BigDecimal;

public class ShippableAndNoExpirableProduct extends NonExpirableProduct implements Shippable {
    private double weight;

    public ShippableAndNoExpirableProduct(String name, BigDecimal price, Integer quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
