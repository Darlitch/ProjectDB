package com.db.project.core.service.impl;

import com.db.project.core.model.Role;
import com.db.project.core.repository.RoleRepository;
import com.db.project.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Value("${role.base-role}")
    private String baseRole;

    @Override
    public Role getBaseRole() {
        return getRoleByName(baseRole);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role \"" + name + "\" not found"));
    }
}
