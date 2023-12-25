package com.stockcontrol.app.services.implementation;

import com.stockcontrol.app.domain.account.Role;
import com.stockcontrol.app.repositories.RoleRepository;
import com.stockcontrol.app.services.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        if (roleRepository.findById(role.getId()).isPresent()) {
            throw new DataIntegrityViolationException("Role already registered");
        };
        return roleRepository.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Role not found");
        });
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role update(String id, Role role) {
        return roleRepository.findById(id).map(dbRole -> {
            dbRole.setDescription(role.getDescription());
            return roleRepository.save(dbRole);
        }).orElseGet(() -> {
            role.setId(id);
            return roleRepository.save(role);
        });
    }

    @Override
    public void delete(String id) {
        roleRepository.deleteById(id);
    }
}
