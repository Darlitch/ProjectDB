package com.db.project.api.dto.jwt;

import lombok.Builder;

@Builder
public record JwtAuthenticationDTO(
        String token,
        String refreshToken
) {
}
