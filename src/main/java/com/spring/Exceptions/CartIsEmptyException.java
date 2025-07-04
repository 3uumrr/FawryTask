package com.spring.Exceptions;

public class CartIsEmptyException extends RuntimeException{
    public CartIsEmptyException(String message){
        super(message);
    }
}
