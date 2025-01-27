package com.example.cargo.Exeception;

public class CustomerSaveException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public CustomerSaveException(String message) {
        super(message);
    }
}

