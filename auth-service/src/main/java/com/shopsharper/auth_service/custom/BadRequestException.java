package com.shopsharper.auth_service.custom;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
