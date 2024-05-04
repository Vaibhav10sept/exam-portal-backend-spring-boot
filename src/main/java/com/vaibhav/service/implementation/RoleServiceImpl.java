package com.vaibhav.service.implementation;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.entity.Role;
import com.vaibhav.repository.RoleRepository;
import com.vaibhav.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role createRole(Role role) {
		System.out.println("creating role");
		Role existingRole = this.findByRoleName(role.getName());
		if(existingRole == null) { 
			System.out.println("creating new role");
			return roleRepository.save(role);
		}
		else {
			System.out.println("return existing role");
			return existingRole;
		}
	}

	@Override
	public Role findByRoleName(String roleName) {
		System.out.println("finding by role name " + roleName);
		List<Role> existingRole = roleRepository.findByName(roleName);
		System.out.println("exisitign role" + existingRole);
		if(existingRole.size() >= 1) return existingRole.get(0);
		return null;
	}

}
