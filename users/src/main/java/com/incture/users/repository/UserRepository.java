package com.incture.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incture.users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
