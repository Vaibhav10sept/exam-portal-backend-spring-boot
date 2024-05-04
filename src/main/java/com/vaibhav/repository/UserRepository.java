package com.vaibhav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaibhav.entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUserName(String userName);
	
	void deleteByUserName(String username);
}
