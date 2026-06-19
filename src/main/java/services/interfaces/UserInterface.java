package services.interfaces;

import entities.User;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getUsers();

    User updateUser(Long id,User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    void deleteUser(Long id);
}
