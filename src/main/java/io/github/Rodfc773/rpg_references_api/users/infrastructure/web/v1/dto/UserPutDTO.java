package io.github.Rodfc773.rpg_references_api.users.infrastructure.web.v1.dto;

import io.github.Rodfc773.rpg_references_api.users.domain.models.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UserPutDTO (
        Optional<String> name,
        Optional<String> email,
        Optional<String> password,
        Optional<RoleEnum> roleEnum
){}
