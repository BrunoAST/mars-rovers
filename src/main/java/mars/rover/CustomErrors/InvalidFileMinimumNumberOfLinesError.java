package mars.rover.CustomErrors;

public class InvalidFileMinimumNumberOfLinesError extends Error {
    public InvalidFileMinimumNumberOfLinesError() {
        super("The file doesn't match the minimal number of lines (3)");
    }
}
