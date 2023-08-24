
public class InvalidPlayerNumberException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidPlayerNumberException(String errorMessage) {
        super(errorMessage);
    }
}