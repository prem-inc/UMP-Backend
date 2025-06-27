package com.incture.users.service.impl;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.incture.users.exception.ResourceNotFoundException;
import com.incture.users.model.User;
import com.incture.users.repository.UserRepository;
import com.incture.users.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}



	@Override
	public User saveUser(User user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreatedAt(now);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}



	@Override
	public User getUserById(long id) {
//		Optional<User> user = userRepository.findById(id);
//		if(user.isPresent()) {
//			return user.get();
//		} else {
//			throw new ResourceNotFoundException("User","Id", id);
//		}
		
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
	}



	@Override
	public User updateUser(User user, long id) {
		User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setDob(user.getDob());
		existingUser.setRole(user.getRole());
		existingUser.setStatus(user.getStatus());
		existingUser.setUserid(user.getUserid());
		userRepository.save(existingUser);
		return existingUser;
	}



	@Override
	public void deleteUser(long id) {
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		userRepository.deleteById(id);
	}
	
}
