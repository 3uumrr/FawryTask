package com.spring.models.cart;

import com.spring.models.product.Product;

import java.math.BigDecimal;

public class CartItem {
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        setTotalPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice() {
        totalPrice = this.product.getPrice().multiply(new BigDecimal(quantity));
    }


}
