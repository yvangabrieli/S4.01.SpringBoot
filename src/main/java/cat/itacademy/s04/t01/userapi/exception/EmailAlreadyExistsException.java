package cat.itacademy.s04.t01.userapi.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("User with email: " + email + "already exists");
    }
}
