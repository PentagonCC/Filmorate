package com.example.filmorate.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorInfo){
        super(errorInfo);
    }
}
