package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDTO(@NotBlank String refreshToken) {
}
