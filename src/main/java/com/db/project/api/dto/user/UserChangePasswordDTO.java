package com.db.project.api.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserChangePasswordDTO(
        @NotBlank String oldPassword,
        @NotBlank String newPassword,
        @NotBlank String confirmPassword
) {
}
