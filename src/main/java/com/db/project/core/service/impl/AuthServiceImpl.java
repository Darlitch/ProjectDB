package com.db.project.core.service.impl;

import com.db.project.api.dto.jwt.JwtAuthenticationDTO;
import com.db.project.api.dto.jwt.RefreshTokenDTO;
import com.db.project.api.dto.user.LoginDTO;
import com.db.project.api.dto.user.UserChangePasswordDTO;
import com.db.project.api.dto.user.UserDto;
import com.db.project.api.dto.user.UserRegisterDTO;
import com.db.project.api.dto.user.UserUpdateDTO;
import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.User;
import com.db.project.core.service.AuthService;
import com.db.project.core.service.JwtService;
import com.db.project.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtAuthenticationDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password()));
        UserDetails user = userService.loadUserByUsername(loginDTO.login());

        return jwtService.generateAuthToken(user.getUsername());
    }

    @Override
    public UserDto registration(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.password().equals(userRegisterDTO.confirmPassword())) {
            throw new ServiceException("passwords do not match", ErrorCode.BAD_REQUEST);
        }

        User user = User.builder()
                .login(userRegisterDTO.login())
                .password(passwordEncoder.encode(userRegisterDTO.password()))
                .build();

        return userService.create(user);
    }

    @Override
    public JwtAuthenticationDTO refresh(RefreshTokenDTO refreshTokenDto) {
        String refreshToken = refreshTokenDto.refreshToken();
        if (refreshToken == null || !jwtService.validateToken(refreshToken)) {
            throw new ServiceException("invalid refresh token", ErrorCode.BAD_REQUEST);
        }

        return jwtService.refreshBaseToken(jwtService.getLoginFromToken(refreshToken), refreshToken);
    }

    @Override
    public UserDto changePassword(UserChangePasswordDTO userChangePasswordDto, UserDetails userDetails) {
        if (!userChangePasswordDto.newPassword().equals(userChangePasswordDto.confirmPassword())) {
            throw new ServiceException("passwords do not match", ErrorCode.BAD_REQUEST);
        }

        User user = userService.getEntityByLogin(userDetails.getUsername());
        if (!passwordEncoder.matches(userChangePasswordDto.oldPassword(), user.getPassword())) {
            throw new ServiceException("old password is incorrect", ErrorCode.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(userChangePasswordDto.newPassword()));

        return userService.update(null);
    }

    @Override
    public UserDto update(UserUpdateDTO userUpdateDto, UserDetails userDetails) {
        User user = userService.getEntityByLogin(userDetails.getUsername());
        if (userUpdateDto.login() != null) {
            user.setLogin(userUpdateDto.login());
        }
        return userService.update(user);
    }

    @Override
    public void delete(UserDetails userDetails) {
        User user = userService.getEntityByLogin(userDetails.getUsername());
        userService.delete(user);
    }
}
