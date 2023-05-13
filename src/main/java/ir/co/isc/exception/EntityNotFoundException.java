package ir.co.isc.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String errorMessage;

    public EntityNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
