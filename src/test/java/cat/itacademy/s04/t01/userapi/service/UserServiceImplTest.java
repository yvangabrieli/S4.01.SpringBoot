package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    User userAda = new User(null, "Ada", "ada@example.com");
    @Test
    void createUser_shouldThrowExceptionWhenEmailAlreadyExists() {
        String email = "ada@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                userService.create(userAda));

        verify(userRepository, never()).save(any());
    }

    @Test
    void createUser_shouldSaveUserWhenEmailDoesNotExist() {
        String email = "ada@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(UUID.randomUUID());
            return u;
        });
        User user = userService.create(userAda);

        assertNotNull(user.getId());
        assertEquals("Ada", user.getName());
        assertEquals(email, user.getEmail());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void getUserById_shouldReturnUserIfExists() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "Ada", "ada@example.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(id);
        assertTrue (result.isPresent());
        assertEquals("Ada", result.get().getName());
    }
}
