package com.stockcontrol.app.services;

import com.stockcontrol.app.domain.account.Role;

import java.util.Collection;

public interface RoleService {
    Role create(Role role);

    Role findById(String id);

    Collection<Role> findAll();

    Role update(String id, Role role);

    void delete(String id);
}
