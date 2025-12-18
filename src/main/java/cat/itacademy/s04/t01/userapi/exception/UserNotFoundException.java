package cat.itacademy.s04.t01.userapi.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("User with id: " + id + " , is not found");
    }
}
