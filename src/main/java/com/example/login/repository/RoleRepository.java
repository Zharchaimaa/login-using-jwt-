package com.example.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.login.model.ERole;
import com.example.login.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
