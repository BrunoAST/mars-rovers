package mars.rover.CustomErrors;

public class InvalidFileTotalNumberOfLines extends Error {
    public InvalidFileTotalNumberOfLines(int lines) {
        super("There is something missing; Expected an odd number of lines but got (" + lines + ") instead");
    }
}
