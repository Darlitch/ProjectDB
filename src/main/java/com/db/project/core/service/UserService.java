package com.db.project.core.service;

import com.db.project.api.dto.role.RoleUpdateDTO;
import com.db.project.api.dto.user.UserDto;
import com.db.project.core.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto create(User userForCreate);

    UserDto update(User userForUpdate);

    UserDto updateRole(int id, RoleUpdateDTO roleUpdateDTO);

    void delete(User userForDelete);

    UserDto getById(int id);

    User getEntityByLogin(String login);

    List<UserDto> getAll();

    @Override
    UserDetails loadUserByUsername(String username);
}
