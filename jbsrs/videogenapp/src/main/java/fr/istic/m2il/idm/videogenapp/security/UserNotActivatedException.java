package fr.istic.m2il.idm.videogenapp.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown input case of a not activated user trying to authenticate.
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
