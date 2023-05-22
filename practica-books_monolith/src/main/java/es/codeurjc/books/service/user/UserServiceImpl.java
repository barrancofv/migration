package es.codeurjc.books.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import es.codeurjc.books.model.User;
import es.codeurjc.books.repository.UserRepository;

@Service
@ConditionalOnProperty(prefix="app.architecture", name="model", value = "USERS_IN_MONOLITH")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository users;

	public void save(User user) {
		users.save(user);
	}

	public void replace(User updatedUser) {
		users.findById(updatedUser.getId()).orElseThrow();
		users.save(updatedUser);
	}

	public List<User> findAll() {
		return users.findAll();
	}

	public Optional<User> findById(long id) {
		return users.findById(id);
	}
	
	public boolean existsById(long id) {
		return users.existsById(id);
	}

	public void deleteById(long id) {
		users.deleteById(id);
	}

	public Optional<User> findByNick(String nick) {
		return users.findByNick(nick);
	}
}
