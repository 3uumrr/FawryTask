package com.spring.Exceptions;

public class NotEnoughQuantityException extends RuntimeException{
    public NotEnoughQuantityException(String message){
        super(message);
    }
}
