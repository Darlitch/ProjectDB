package com.db.project.api.mapper.role;

import com.db.project.api.dto.role.RoleDTO;
import com.db.project.core.model.Role;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleDTO toDto(Role client);

    List<RoleDTO> toDto(List<Role> clients);
}
