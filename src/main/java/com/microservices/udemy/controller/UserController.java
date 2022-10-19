package com.microservices.udemy.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.udemy.dao.UserDAOService;
import com.microservices.udemy.entity.User;
import com.microservices.udemy.exception.UserNotFoundException;

@RestController
public class UserController {

	private UserDAOService userdao;

	// Constructor Injection
	public UserController(UserDAOService userdao) {
		this.userdao = userdao;
	}

	@GetMapping("/users")
	public List<User> getAlluser() {
		return userdao.findAll();
	}

	@GetMapping("/users/{id}")
	public User findUserByID(@PathVariable int id) {
		User user = userdao.findById(id);

		if (user == null) {
			throw new UserNotFoundException(" User is not found for id : " + id);
		}
		return user;
	}

//	@PostMapping("/addUser")
//	public void addUSer(@RequestBody User user) {
//		userdao.addUser(user);
//	}

	@PostMapping("/users")
	public ResponseEntity<User> addUSer(@Valid @RequestBody User user) {
		User addedUser = userdao.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(addedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deletByid(@PathVariable int id) {
		userdao.deltByid(id);

	}
}
