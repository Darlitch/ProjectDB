package com.db.project.core.service;

import com.db.project.core.model.Role;

public interface RoleService {

    Role getBaseRole();

    Role getRoleByName(String name);
}
