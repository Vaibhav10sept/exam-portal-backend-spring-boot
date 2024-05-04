package com.vaibhav.service;

import com.vaibhav.entity.Role;

public interface RoleService {
	Role createRole(Role role);
	
	Role findByRoleName(String roleName);
}
