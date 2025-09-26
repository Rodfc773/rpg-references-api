package io.github.Rodfc773.rpg_references_api.users.domain.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super(message);
    }
}
