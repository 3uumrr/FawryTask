package com.spring.Exceptions;

public class ProductExpiredException extends RuntimeException{
    public ProductExpiredException(String message){
        super(message);
    }
}
