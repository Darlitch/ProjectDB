package com.db.project.core.service;


import com.db.project.api.dto.jwt.JwtAuthenticationDTO;
import com.db.project.api.dto.jwt.RefreshTokenDTO;
import com.db.project.api.dto.user.LoginDTO;
import com.db.project.api.dto.user.UserChangePasswordDTO;
import com.db.project.api.dto.user.UserDto;
import com.db.project.api.dto.user.UserRegisterDTO;
import com.db.project.api.dto.user.UserUpdateDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    JwtAuthenticationDTO login(LoginDTO loginDTO);

    UserDto registration(UserRegisterDTO userRegisterDTO);

    JwtAuthenticationDTO refresh(RefreshTokenDTO refreshTokenDto);

    UserDto changePassword(UserChangePasswordDTO userChangePasswordDto, UserDetails userDetails);

    UserDto update(UserUpdateDTO userUpdateDto, UserDetails userDetails);

    void delete(UserDetails userDetails);
}
