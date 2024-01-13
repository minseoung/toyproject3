package toy.toyproject3.exception;

public class AlreadyExistLoginIdException extends RuntimeException{
    public AlreadyExistLoginIdException() {
        super();
    }

    public AlreadyExistLoginIdException(String message) {
        super(message);
    }

    public AlreadyExistLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistLoginIdException(Throwable cause) {
        super(cause);
    }
}
