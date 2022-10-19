package com.microservices.udemy.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.udemy.entity.Post;
import com.microservices.udemy.entity.User;
import com.microservices.udemy.exception.UserNotFoundException;
import com.microservices.udemy.repository.PostJpaRepository;
import com.microservices.udemy.repository.UserJpaRepository;

@RestController
public class JpaUserController {

	private UserJpaRepository repository;

	private PostJpaRepository postResitory;

	// Constructor Injection
	public JpaUserController(UserJpaRepository repository, PostJpaRepository postResitory) {
		this.repository = repository;
		this.postResitory = postResitory;
	}

	@GetMapping("/jpa/users")
	public List<User> getAlluser() {
		return repository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User findUserByID(@PathVariable int id) {
		Optional<User> user = repository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException(" User is not found for id : " + id);
		}
		return user.get();
	}

//	@PostMapping("/addUser")
//	public void addUSer(@RequestBody User user) {
//		userdao.addUser(user);
//	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUSer(@Valid @RequestBody User user) {
		User addedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(addedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deletByid(@PathVariable int id) {
		repository.deleteById(id);

	}

	@PostMapping("/jpa/users/{id}/posts")
	public List<Post> getPostByUserId(@PathVariable int id) {
		Optional<User> userId = repository.findById(id);
		if (userId.isEmpty()) {
			throw new UserNotFoundException("User not found with id : " + id);
		}
		return userId.get().getPost();

	}

	@PostMapping("/jpa/users/{id}/addposts")
	public List<Post> addPostByUserId(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userId = repository.findById(id);
		if (userId.isEmpty()) {
			throw new UserNotFoundException("User not found with id : " + id);
		}
		post.setUser(userId.get());
		postResitory.save(post);
		return userId.get().getPost();

	}
}
