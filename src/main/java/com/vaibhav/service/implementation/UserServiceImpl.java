package com.vaibhav.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaibhav.entity.Role;
import com.vaibhav.entity.User;
import com.vaibhav.repository.RoleRepository;
import com.vaibhav.repository.UserRepository;
import com.vaibhav.service.RoleService;
import com.vaibhav.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user, Role newRole) throws Exception {	
		User existingUser = userRepository.findByUserName(user.getUserName());
		if(existingUser != null) {
			throw new Exception("user with this user name already exists");
		}
		else { //create new user
			user.getRoles().add(newRole);
			// Save the roles associated with the employee first
	        for (Role role : user.getRoles()) {
	            Role existingRole = roleService.findByRoleName(role.getName());
	            if (existingRole == null) {
	                // If the role doesn't exist, save it
	                roleService.createRole(role);
	            } else {
	                // If the role exists, update the reference to the existing role
	                role.setId(existingRole.getId());
	            }
	        }
	        // Save the employee entity after ensuring all roles are saved or updated
//	        employeeRepository.save(employee);
//			roleService.createRole(role);
	        System.out.println("geting pasword: "+ user.getPassword());
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
			existingUser = userRepository.save(user);
		}
		return existingUser;
	}

	@Override
	public User findUserByUserName(String userName) throws Exception {
		User existingUser = userRepository.findByUserName(userName);
		if(existingUser == null) {
			throw new Exception("user with this user name doesn't exists");
		}
		
		return existingUser;
	}
	
	@Override
	public User findUserById(Long id) throws Exception {
		Optional<User> existingUser = userRepository.findById(id);
		if(!existingUser.isPresent()) {
			throw new Exception("this user doesn't exists");
		}
		
		return existingUser.get();
	}

	@Override
	public User updateUser(User user) throws Exception {
		User existingUser = this.findUserByUserName(user.getUserName());
		user.setId(existingUser.getId());
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void deleteById(Long id) throws Exception {
		this.findUserById(id);
		userRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteByUserName(String userName) throws Exception {
		this.findUserByUserName(userName);
		userRepository.deleteByUserName(userName);
	}
	

}
