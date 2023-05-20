package es.codeurjc.mtm.ms.service;

import es.codeurjc.mtm.ms.model.User;
import es.codeurjc.mtm.ms.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleDataLoader {

	
	@Autowired
	private UserRepository users;

	@PostConstruct
	public void init() {

		User user1 = new User("pepe", "pepe@gmail.com");
		User user2 = new User("juan", "juan@hotmail.com");
		User user3 = new User("rafa", "rafa85@terra.es");

		users.save(user1);
		users.save(user2);
		users.save(user3);
	}

}
