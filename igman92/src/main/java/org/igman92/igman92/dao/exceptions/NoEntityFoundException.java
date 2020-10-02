package org.igman92.igman92.dao.exceptions;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException() {
    }

    public NoEntityFoundException(String message) {
        super(message);
    }

    public NoEntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
