package org.igman92.igman92.dao.exceptions;

public class MultipleEntitiesFoundException extends RuntimeException{

    public MultipleEntitiesFoundException() {
    }

    public MultipleEntitiesFoundException(String message) {
        super(message);
    }

    public MultipleEntitiesFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
