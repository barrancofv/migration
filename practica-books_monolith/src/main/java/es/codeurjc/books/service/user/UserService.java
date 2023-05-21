package es.codeurjc.books.service.user;

import es.codeurjc.books.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void save(User user);

    public void replace(User updatedUser);

    public List<User> findAll();

    public Optional<User> findById(long id);

    public boolean existsById(long id);

    public void deleteById(long id);

    public Optional<User> findByNick(String nick);
}
