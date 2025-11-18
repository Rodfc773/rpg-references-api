package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record NewAccessTokenResponseDTO(@NotBlank String accessToken) {
}
