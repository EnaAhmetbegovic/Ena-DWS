package org.igman92.igman92.dao.exceptions;

public class ProjectException extends RuntimeException {

    public ProjectException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ProjectException(Exception e) {
        super(e);
    }

    public ProjectException(String message) {
        super(message);
    }
}
