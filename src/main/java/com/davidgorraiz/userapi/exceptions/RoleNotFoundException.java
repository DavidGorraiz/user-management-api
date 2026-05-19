package com.davidgorraiz.userapi.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(long id) {
        super("Role with id: " + id + " not found");
    }
}
