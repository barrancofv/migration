package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;
import es.codeurjc.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
//@ConditionalOnProperty(prefix="app.architecture", value = "USERS_IN_MICROSERVICE")
public class UserServiceMSImpl {

	@Autowired
	RestTemplate restTemplate;

	/*public void save(User user) {
		users.save(user);
	}

	public void replace(User updatedUser) {
		users.findById(updatedUser.getId()).orElseThrow();
		users.save(updatedUser);
	}

	public List<User> findAll() {
		return users.findAll();
	}*/

	public Optional<User> findById(long id) {

		String resourceUrl = "http://localhost:8082/users/" + id;
		User response = restTemplate.getForObject(resourceUrl, User.class);
		return Optional.ofNullable(response);
	}
	
	/*public boolean existsById(long id) {
		return users.existsById(id);
	}

	public void deleteById(long id) {
		users.deleteById(id);
	}

	public Optional<User> findByNick(String nick) {
		return users.findByNick(nick);
	}*/
}
