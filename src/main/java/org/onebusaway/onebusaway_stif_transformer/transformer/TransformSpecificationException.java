package org.onebusaway.onebusaway_stif_transformer.transformer;

public class TransformSpecificationException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String line;

    public TransformSpecificationException(String message, String line) {
        super(message);
        this.line = line;
    }

    public TransformSpecificationException(String message, Throwable ex,
                                           String line) {
        super(message, ex);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
