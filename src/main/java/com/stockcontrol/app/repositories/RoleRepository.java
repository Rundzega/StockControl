package com.stockcontrol.app.repositories;

import com.stockcontrol.app.domain.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}