package io.github.rodfc773.rpgreferencesapi.users.domain.models;

public enum RoleEnum {
    PLAYER("Jogador comum"),
    GAME_MASTER("Mestre de mesa"),
    NATSU("Singularidade de classe 0"),
    CO_MASTER("Gerente de Mesa");

    private final String description;

    RoleEnum(String description) { this.description = description; }

    public String getDescription() { return description; }
}
