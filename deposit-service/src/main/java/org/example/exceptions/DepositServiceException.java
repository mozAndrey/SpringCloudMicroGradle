package org.example.exceptions;

public class DepositServiceException extends RuntimeException {
    public DepositServiceException(String message) {
        super(message);
    }
}
