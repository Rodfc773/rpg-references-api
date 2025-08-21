package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto;

import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;

public record UserResponseDTO(String id, String name, String email, RoleEnum role) {}
