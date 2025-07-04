package com.spring.services;

import com.spring.models.product.Shippable;

import java.util.List;

public class ShippingService {

    public static double shippingFees(List<Shippable> products){
        return products.stream()
                .map(p -> p.getWeight())
                .reduce(0.0, Double::sum) *  0.02727;
    }

}
