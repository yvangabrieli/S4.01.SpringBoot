package cat.itacademy.s04.t01.userapi.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import cat.itacademy.s04.t01.userapi.models.User;

public interface UserService {
    User create (User user);
    List<User> findAll ();
    Optional<User> findById (UUID id);
    Optional<User> findByName (String name);
}
