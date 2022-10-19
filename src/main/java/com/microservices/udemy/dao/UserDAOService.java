package com.microservices.udemy.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.microservices.udemy.entity.User;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();

	private static int userCount = 0;
	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Nick", LocalDate.now().minusYears(24)));
		users.add(new User(++userCount, "Josh", LocalDate.now().minusYears(25)));

	}

	public List<User> findAll() {
		return users;
	}

	public User findById(int id) {

		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public User addUser(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public void deltByid(int id) {

		Predicate<? super User> prediacte = user -> user.getId().equals(id);
		users.removeIf(prediacte);
	}
}
