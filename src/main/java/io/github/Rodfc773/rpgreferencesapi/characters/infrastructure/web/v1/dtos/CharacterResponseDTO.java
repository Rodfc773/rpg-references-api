package io.github.rodfc773.rpgreferencesapi.characters.infrastructure.web.v1.dtos;

public record CharacterResponseDTO(
        String id,
        String name,
        String profilePhotoUrl,
        int level,
        String alignment
) {
}
