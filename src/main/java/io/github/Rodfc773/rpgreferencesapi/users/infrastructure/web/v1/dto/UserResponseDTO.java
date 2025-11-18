package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import io.github.rodfc773.rpgreferencesapi.users.domain.models.RoleEnum;

public record UserResponseDTO(String id, String name, String email, RoleEnum role) {}
