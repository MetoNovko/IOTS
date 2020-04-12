package com.mps.transferables.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Email already in use");
    }
}