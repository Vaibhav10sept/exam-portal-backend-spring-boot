package com.vaibhav.controller;

import java.util.List;

import org.antlr.v4.runtime.misc.TestRig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.vaibhav.entity.Role;
import com.vaibhav.entity.User;
import com.vaibhav.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		Role role = new Role("NORMAL_USER");
		User createdUser;
		try {
			createdUser = userService.createUser(user, role);
		} catch (Exception e) {
//			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: " + e.getMessage());
		}
		
		return ResponseEntity.ok(createdUser);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
		User existingUser;
		try {
			existingUser = userService.findUserByUserName(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch user: " + e.getMessage());
		}
		return ResponseEntity.ok(existingUser);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> deleteUserByUserName(@PathVariable Long id)  {
//		try {
//			userService.deleteById(id);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete user: " + e.getMessage());
//		}
//		return ResponseEntity.ok("user deleted successfully");
//	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<String> deleteUserByUserName(@PathVariable String userName)  {
		try {
			userService.deleteByUserName(userName);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete user: " + e.getMessage());
		}
		return ResponseEntity.ok("user deleted successfully");
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> usersList = userService.findAllUser();
		return ResponseEntity.ok(usersList);
	}
	
	@PutMapping() 
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		User updatedUser;
		try {
			updatedUser = userService.updateUser(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed to update user: " + e.getMessage());
		}
		return ResponseEntity.ok(updatedUser);
	}
}
