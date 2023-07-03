package com.devnatao.library.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.library.model.UserModel;
import com.devnatao.library.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDetailsServiceImpl service; 
	
	@PostMapping("/register")
	public ResponseEntity<UserModel> registerNewUser(@RequestBody UserModel data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserModel> updateUserPassword(@PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.updateUserPassword(id));
	}
}
