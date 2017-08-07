package ru.caf82.result.exceptions;

/**
 * Created by kinetik on 07.08.17.
 */
public class ModelNotFittedException extends Exception {

    public ModelNotFittedException() { super(); }
    public ModelNotFittedException(String message) { super(message); }
    public ModelNotFittedException(String message, Throwable cause) { super(message, cause); }
    public ModelNotFittedException(Throwable cause) { super(cause); }
}
