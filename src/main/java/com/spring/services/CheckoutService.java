package com.spring.services;

import com.spring.Exceptions.CartIsEmptyException;
import com.spring.Exceptions.InsufficientBalanceException;
import com.spring.Exceptions.NotEnoughQuantityException;
import com.spring.models.cart.Cart;
import com.spring.models.cart.CartItem;
import com.spring.models.customer.Customer;
import com.spring.models.product.ExpirableProduct;
import com.spring.models.product.Product;
import com.spring.models.product.Shippable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CheckoutService {
    public static void checkout(Customer customer , Cart cart){

        validateCartNotEmpty(cart); // Check If Cart Is Empty

        validateCustomerHasEnoughBalance(cart.getTotalAmount().doubleValue(),customer.getBalance()); // Check If Customer Has ُُEnough Money

        // Get Expirable Products
        List<ExpirableProduct> expirableProduct = cart.getCartItems().stream()
                .map(CartItem::getProduct)
                .filter(product -> product instanceof ExpirableProduct)
                .map(product -> (ExpirableProduct) product)
                .toList();

        validateProductNotExpired(expirableProduct); // Check If Product Was ُُExpired

        validateProductOutOfStock(cart.getCartItems()); // Check If Product Was ُُOut Of Stock

        // Get Shippable Products
        List<Product> shippableProduct = cart.getCartItems().stream()
                .map(CartItem::getProduct)
                .filter(product -> product instanceof Shippable)
                .toList();

        // Calculate Shipping Fees
        double shipping =  ShippingService.shippingFees(shippableProduct);

        // Deduct Money From The Customer
        customer.setBalance(customer.getBalance() - cart.getTotalAmount().doubleValue() - shipping);


        // Print Receipt
        printReceipt(cart,customer);

    }


    // Check If Cart Is Empty
    private static void validateCartNotEmpty(Cart cart){
        if(cart == null)
            throw new CartIsEmptyException("Cart Is Empty :(");
    }

    // Check If Customer Has ُُEnough Money
    private static void validateCustomerHasEnoughBalance(double totalAmount, double balance){
        if(totalAmount > balance)
            throw new InsufficientBalanceException("Customer Balance Is Insufficient :(");
    }


    // Check If Product Was ُُExpired
    private static void validateProductNotExpired(List<ExpirableProduct> products){
        ExpirableProduct firstProductExpired = products.stream()
                .filter(product -> LocalDate.now().isAfter(product.getExpireDate()))
                .findFirst()
                .orElse(null);

        if (firstProductExpired != null)
            throw new InsufficientBalanceException("Product Is Expired :(");
    }


    // Check If Product Was ُُOut Of Stock
    private static void validateProductOutOfStock(Set<CartItem> cartItems){
        Product product = cartItems.stream()
                .filter(cartItem -> cartItem.getQuantity() > cartItem.getProduct().getQuantity())
                .map(CartItem::getProduct)
                .findFirst()
                .orElse(null);

        if(product != null) {
            throw  new NotEnoughQuantityException("Not Enough Quantity For Product: " + product.getName());
        }

    }


    // Print Receipt
    private static void printReceipt(Cart cart , Customer customer) {
        List<CartItem> cartItems = cart.getCartItems()
                .stream()
                .filter(product -> product.getProduct() instanceof Shippable)
                .toList();

        System.out.println("** Shipment notice **");
        for (CartItem cartItem : cartItems){
            if (cartItem.getProduct() instanceof Shippable) {
                // Calculate Total Weight For Each Cart Items
                double totalWeight = ((Shippable) cartItem.getProduct()).getWeight() * cartItem.getQuantity();
                System.out.println(cartItem.getQuantity() + "x " + cartItem.getProduct().getName() + "\t\t" + totalWeight + "g");
            } }

        // Calculate Total Weight For Cart
        double totalWeight = cartItems.stream()
                .map(cartItem -> ((Shippable) cartItem.getProduct()).getWeight() * cartItem.getQuantity())
                .reduce(0.0, Double::sum);

        System.out.println("Total package weight " + totalWeight/1000 + "Kg \n");

        System.out.println("** Checkout receipt **");
        for (CartItem cartItem : cartItems){
            System.out.println(cartItem.getQuantity() + "x " + cartItem.getProduct().getName() + "\t\t" + (cartItem.getTotalPrice()));
        }

        // Calculate Sub Total For Shippable Products
        BigDecimal subtotal = cartItems.stream()
                        .filter(item -> item.getProduct() instanceof Shippable)
                    .map(CartItem::getTotalPrice)
                    .reduce(BigDecimal.ZERO,BigDecimal::add);

        List<Product> shippableProduct = cart.getCartItems().stream()
                .map(CartItem::getProduct)
                .filter(product -> product instanceof Shippable)
                .toList();

        System.out.println("-------------------------------");
        System.out.println("Subtotal " + subtotal);
        double shipping =  ShippingService.shippingFees(shippableProduct);
        System.out.println("Shipping " + shipping);
        System.out.println("Amount " + (subtotal.add(new BigDecimal(shipping))));
        System.out.println("Customer Current Balance After Payment : " + customer.getBalance());


    }

}
