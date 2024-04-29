package com.tri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tri.config.JwtProvider;
import com.tri.model.User;
import com.tri.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email = jwtProvider.getEmaiFromJwtToken(jwt);
		User user = findUserByEmail(email);
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new Exception("User not found.");
		}
		return user;
	}
}
