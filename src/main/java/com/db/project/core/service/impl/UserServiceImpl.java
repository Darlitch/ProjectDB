package com.db.project.core.service.impl;

import com.db.project.api.dto.role.RoleUpdateDTO;
import com.db.project.api.dto.user.UserDto;
import com.db.project.api.mapper.user.UserMapper;
import com.db.project.core.exception.ErrorCode;
import com.db.project.core.exception.ServiceException;
import com.db.project.core.model.Role;
import com.db.project.core.model.User;
import com.db.project.core.repository.UserRepository;
import com.db.project.core.service.RoleService;
import com.db.project.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto getById(int id) {
        return userMapper.toDto(getEntityById(id));
    }

    @Override
    public User getEntityByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new ServiceException("User with login \"" + login + "\" not found", ErrorCode.NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getEntityByLogin(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole().getName()))
                .build();
    }

    @Override
    @Transactional
    public UserDto create(User userForCreate) {
        if (userRepository.existsByLogin(userForCreate.getLogin())) {
            throw new ServiceException("User with login " + userForCreate.getLogin() + " already exists", ErrorCode.BAD_REQUEST);
        }

        userForCreate.setRole(roleService.getBaseRole());
        return userMapper.toDto(userRepository.save(userForCreate));
    }

    @Override
    @Transactional
    public UserDto update(User userForUpdate) {
        User user = getEntityByLogin(userForUpdate.getLogin());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateRole(int id, RoleUpdateDTO roleUpdateDTO) {
        Role newRole = roleService.getRoleByName(roleUpdateDTO.getName());
        User user = getEntityById(id);
        user.setRole(newRole);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(User userForDelete) {
        User user = getEntityByLogin(userForDelete.getLogin());
        userRepository.delete(user);
    }

    private User getEntityById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id " + id + " not found", ErrorCode.NOT_FOUND));
    }
}
