package com.example.CRUD.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("No User with the id: "+id);
    }
}
