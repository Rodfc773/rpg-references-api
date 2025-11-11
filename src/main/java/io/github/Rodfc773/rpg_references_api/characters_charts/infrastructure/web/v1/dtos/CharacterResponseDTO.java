package io.github.Rodfc773.rpg_references_api.characters_charts.infrastructure.web.v1.dtos;

public record CharacterResponseDTO(
        String id,
        String name,
        String profilePhotoUrl,
        int level,
        String alignment
) {
}
