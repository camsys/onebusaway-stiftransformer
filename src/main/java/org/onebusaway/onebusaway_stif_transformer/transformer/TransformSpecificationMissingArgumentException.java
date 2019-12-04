package org.onebusaway.onebusaway_stif_transformer.transformer;

public class TransformSpecificationMissingArgumentException extends
        TransformSpecificationException {
    public TransformSpecificationMissingArgumentException(String line, String name) {
        super("missing required argument: \"" + name + "\"", line);
    }
}
