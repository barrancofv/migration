package es.codeurjc.mtm.ms.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import es.codeurjc.mtm.ms.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.mtm.ms.model.User;
import es.codeurjc.mtm.ms.service.UserService;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

	@Autowired
	private UserService users;

	public ResponseEntity<?> createUser(@RequestBody User user) {

		try {

			users.save(user);

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest()
					.body("User nick should be unique");
		}

		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).body(user);
	}

	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable long id) {

		newUser.setId(id);
		users.replace(newUser);
		return newUser;
	}

	@GetMapping("/users/")
	public List<User> getUsers() {

		return users.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		return users.findById(id).orElseThrow();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) {

		User user = users.findById(id).orElseThrow();

		List<Comment> comments = this.getCommentByBook(id);
		if (comments.isEmpty()) {
			users.deleteById(id);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
		}
	}

	private List<Comment> getCommentByBook(long id) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8081/users/" + id + "/comments";
		Comment[] response = restTemplate.getForObject(resourceUrl, Comment[].class);
		List<Comment> comments = Arrays.asList(response);
		return comments;
	}

}