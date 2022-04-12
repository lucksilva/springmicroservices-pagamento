package com.example.microservicespring.pagamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    public ResourceNotFoundException(String exeption) {
        super(exeption);
    }
}
