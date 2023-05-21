package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;
import es.codeurjc.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
//@ConditionalOnProperty(prefix="app.architecture", value = "USERS_IN_MICROSERVICE")
public class UserServiceMSImpl {

	@Autowired
	RestTemplate restTemplate;

	public void save(User user) {
		String resourceUrl = "http://localhost:8082/users/";
		restTemplate.put(resourceUrl, user);
	}

	public void replace(User updatedUser) {
		String resourceUrl = "http://localhost:8082/users/" + updatedUser.getId();
		User user = restTemplate.getForObject(resourceUrl, User.class);
		restTemplate.put(resourceUrl, user);
	}

	public List<User> findAll() {
		String resourceUrl = "http://localhost:8082/users/";
		User[] response = restTemplate.getForObject(resourceUrl, User[].class);
		List<User> users = Arrays.asList(response);
		return users;
	}

	public Optional<User> findById(long id) {
		String resourceUrl = "http://localhost:8082/users/" + id;
		User response = restTemplate.getForObject(resourceUrl, User.class);
		return Optional.ofNullable(response);
	}
	
	public boolean existsById(long id) {

		String resourceUrl = "http://localhost:8082/users/" + id;
		User response = restTemplate.getForObject(resourceUrl, User.class);
		return response != null ? true : false;
	}

	public void deleteById(long id) {
		String resourceUrl = "http://localhost:8082/users/" + id;
		restTemplate.delete(resourceUrl);
	}
	
	public Optional<User> findByNick(String nick) {
		String resourceUrl = "http://localhost:8082/users/nick/" + nick;
		User response = restTemplate.getForObject(resourceUrl, User.class);
		return Optional.ofNullable(response);
	}
}
