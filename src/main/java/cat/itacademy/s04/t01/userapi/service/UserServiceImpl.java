package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exception.EmailAlreadyExistsException;
import cat.itacademy.s04.t01.userapi.exception.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.searchByName(name);
    }

}

