package br.edu.utfpr.tsi.sd.core.errors;

public class ParseDtoException extends RuntimeException {
    public ParseDtoException(String message) {
        super(message);
    }

    public ParseDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}
