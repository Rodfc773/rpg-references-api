package io.github.rodfc773.rpgreferencesapi.users.domain.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
