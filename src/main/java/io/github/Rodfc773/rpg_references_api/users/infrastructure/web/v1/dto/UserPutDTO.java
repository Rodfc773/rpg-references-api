package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto;

import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPutDTO (
        @NotBlank(message = "The name cannot be blank")
        String name,
        @NotBlank(message = "The email cannot be blank")
        String email,
        @Size(min = 8, max = 100, message = "Password must be at least 8 chars")
        String password,
        @NotBlank(message = "The role cannot be blank")
        RoleEnum roleEnum
){}
