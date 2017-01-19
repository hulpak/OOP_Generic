package spreadtest.Grammar.Coordinates;
public class CoordinateFormatException extends Exception {
    public CoordinateFormatException() {
    }

    public CoordinateFormatException(String message) {
        super(message);
    }

    public CoordinateFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinateFormatException(Throwable cause) {
        super(cause);
    }
}
