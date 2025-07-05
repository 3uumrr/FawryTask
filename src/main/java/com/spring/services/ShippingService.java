package com.spring.services;

import com.spring.models.cart.CartItem;
import com.spring.models.product.Product;
import com.spring.models.product.Shippable;

import java.util.List;

public class ShippingService {

    public static double shippingFees(List<Product> shippableProduct){
        return shippableProduct.stream()
                .map(product -> (Shippable) product)
                .map(p -> p.getWeight() * ((Product) p).getQuantity()/100) // Simple Logic To Calculate Shipping Fees
                .reduce(0.0, Double::sum);
    }

}
