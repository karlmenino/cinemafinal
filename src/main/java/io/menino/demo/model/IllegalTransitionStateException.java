package io.menino.demo.model;

public class IllegalTransitionStateException extends Exception {
    public IllegalTransitionStateException(String error_transition) {
        super(error_transition);
    }
}
