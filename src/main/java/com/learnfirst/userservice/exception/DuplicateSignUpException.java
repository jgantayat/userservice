package com.learnfirst.userservice.exception;

public class DuplicateSignUpException extends RuntimeException{

    public DuplicateSignUpException(String message) {
        super(message);
    }
}
