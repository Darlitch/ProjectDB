package com.db.project.api.controller;

import com.db.project.api.dto.jwt.JwtAuthenticationDTO;
import com.db.project.api.dto.jwt.RefreshTokenDTO;
import com.db.project.api.dto.user.LoginDTO;
import com.db.project.api.dto.user.UserChangePasswordDTO;
import com.db.project.api.dto.user.UserDto;
import com.db.project.api.dto.user.UserRegisterDTO;
import com.db.project.api.dto.user.UserUpdateDTO;
import com.db.project.core.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtAuthenticationDTO login(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDTO refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDto) {
        return authService.refresh(refreshTokenDto);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        return authService.registration(userRegisterDTO);
    }

    @PatchMapping("/change-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDto, @AuthenticationPrincipal UserDetails userDetails) {
        authService.changePassword(userChangePasswordDto, userDetails);
    }

    @PatchMapping
    public UserDto update(@Valid @RequestBody UserUpdateDTO userUpdateDto, @AuthenticationPrincipal UserDetails userDetails) {
        return authService.update(userUpdateDto, userDetails);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal UserDetails userDetails) {
        authService.delete(userDetails);
    }
}
