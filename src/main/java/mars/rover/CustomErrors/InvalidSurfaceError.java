package mars.rover.CustomErrors;

public class InvalidSurfaceError extends Error {
    public InvalidSurfaceError(String surface) {
        super("Invalid surface: " + surface);
    }
}
