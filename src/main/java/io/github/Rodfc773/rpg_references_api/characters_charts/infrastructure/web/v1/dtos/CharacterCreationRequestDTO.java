package io.github.Rodfc773.rpg_references_api.characters_charts.infrastructure.web.v1.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CharacterCreationRequestDTO(
        @NotBlank @Size(min = 2, max = 100, message = "the name needs to be between 2 and 100 chars")
        String name,
        String profilePhotoUrl,
        @Min(value = 1, message = "The level needs to be at least 1.") int level,
        @NotBlank String alignment,
        String bloodType
) {
}
