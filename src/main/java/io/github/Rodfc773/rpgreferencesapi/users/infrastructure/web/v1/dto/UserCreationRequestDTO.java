package io.github.rodfc773.rpgreferencesapi.users.infrastructure.web.v1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreationRequestDTO(
        @NotBlank(message = "the field name cannot be blank")
        @NotNull(message = "the field name cannot be null")
        String name,
        @NotNull(message = "the field password cannot be null")
        @Size(min = 8, max = 100, message = "the field password must be between 8 and 100 characters")
        String password,

        @Email(message = "the field email must be a valid email")
        String email) { }
