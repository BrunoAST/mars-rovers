package mars.rover.CustomErrors;

public class InvalidInstructionsError extends Error {
    public InvalidInstructionsError(String instruction) {
        super("Invalid instructions: " + instruction);
    }
}
