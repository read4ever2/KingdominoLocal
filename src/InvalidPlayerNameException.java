
public class InvalidPlayerNameException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidPlayerNameException(String errorMessage) {
        super(errorMessage);
    }
}
