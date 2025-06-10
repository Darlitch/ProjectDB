package com.db.project.api.mapper.user;

import com.db.project.api.dto.user.UserDto;
import com.db.project.api.mapper.role.RoleMapper;
import com.db.project.core.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = RoleMapper.class)
public interface UserMapper {

    UserDto toDto(User client);

    List<UserDto> toDto(List<User> clients);
}
