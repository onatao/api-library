package com.devnatao.library.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnatao.library.model.UserModel;
import com.devnatao.library.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = repository.findByUsername(username).orElseThrow(
					() -> new UsernameNotFoundException("Username not found!"));
		
		return user;
	}
	
	@Bean
	public PasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	public UserModel create(UserModel data) {
		data.setPassword(encode()
						.encode(data.getPassword()));
		data.setId(UUID.randomUUID());
		
		return repository.save(data);
	}
	
	public void deleteById(UUID id) {
		repository.deleteById(id);
	}
	
	public UserModel updateUserPassword(UUID id) {
		Optional<UserModel> user = repository.findById(id);
		var userModel = user.get();
		
		if (user.isEmpty()) throw new UsernameNotFoundException("User doesnt exist!");
	
		if (!userModel.getUsername().equals(userModel.getUsername())) {
			throw new UsernameNotFoundException("Username cannot be changed");
		}
		
		userModel.setUsername(user.get().getUsername());
		
		user.get().setPassword(encode().
			  	        encode(user.get().getPassword()));
		
		userModel.setPassword(user.get().getPassword());
		return userModel;
	}
}
