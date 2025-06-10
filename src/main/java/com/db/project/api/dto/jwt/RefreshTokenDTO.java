package com.db.project.api.dto.jwt;

import lombok.Builder;

@Builder
public record RefreshTokenDTO(
        String refreshToken
) {
}
