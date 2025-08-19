package io.github.Rodfc773.rpg_references_api.users.domain.models;

public enum RoleEnum {
    PLAYER("Jogador comum"),
    GAME_MASTER("Mestre de mesa"),
    NATSU("O Iluminado");

    private final String description;

    RoleEnum(String description) { this.description = description; }

    public String getDescription() { return description; }
}
