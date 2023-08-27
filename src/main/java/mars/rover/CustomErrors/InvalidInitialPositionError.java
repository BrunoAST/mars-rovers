package mars.rover.CustomErrors;

public class InvalidInitialPositionError extends Error {
    public InvalidInitialPositionError(String initialPosition) {
        super("Invalid initial position: " + initialPosition);
    }
}
