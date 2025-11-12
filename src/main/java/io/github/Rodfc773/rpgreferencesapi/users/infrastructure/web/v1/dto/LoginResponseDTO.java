package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record LoginResponseDTO(String accessToken, String refreshToken, String type, LocalDateTime requestTime) {
}
