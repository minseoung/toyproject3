package toy.toyproject3.exception;

public class MemberNotFound extends RuntimeException{
    public MemberNotFound() {
        super();
    }

    public MemberNotFound(String message) {
        super(message);
    }

    public MemberNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotFound(Throwable cause) {
        super(cause);
    }
}
