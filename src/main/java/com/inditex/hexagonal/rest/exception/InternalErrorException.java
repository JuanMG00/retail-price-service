package com.inditex.hexagonal.rest.exception;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String msg) {
        super(msg);
    }
}
