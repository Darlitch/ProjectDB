package com.db.project.api.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank String login,
        @NotBlank String password,
        @NotBlank String confirmPassword
) {
}
