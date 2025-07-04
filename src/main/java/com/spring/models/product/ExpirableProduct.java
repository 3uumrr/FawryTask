package com.spring.models.product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpirableProduct extends Product {
    private LocalDate expireDate;

    public ExpirableProduct(String name, BigDecimal price, Integer quantity, LocalDate expireDate) {
        super(name, price, quantity);
        this.expireDate = expireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
}
