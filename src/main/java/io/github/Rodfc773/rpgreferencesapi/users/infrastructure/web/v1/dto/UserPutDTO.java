package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import io.github.rodfc773.rpgreferencesapi.users.domain.models.RoleEnum;

import java.util.Optional;

public record UserPutDTO (
        Optional<String> name,
        Optional<String> email,
        Optional<String> password,
        Optional<RoleEnum> roleEnum
){}
