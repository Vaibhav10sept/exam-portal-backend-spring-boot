package com.vaibhav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaibhav.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	List<Role> findByName(String roleName);

}
