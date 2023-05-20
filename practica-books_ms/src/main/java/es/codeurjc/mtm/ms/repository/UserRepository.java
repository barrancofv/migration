package es.codeurjc.mtm.ms.repository;

import java.util.Optional;

import es.codeurjc.mtm.ms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNick(String nick);

}