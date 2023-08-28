package mars.rover.Normalizer;

import mars.rover.Models.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NormalizeTest {
    @Test
    @DisplayName("Should successfully normalize a valid input")
    void shouldSuccessfullyNormalize() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("5 6");
        lines.add("1 2 N");
        lines.add("LMLMLMLMM");
        lines.add("3 3 E");
        lines.add("MMRMMRMRRM");

        var normalizedData = Normalize.normalizeData(lines);
        var surface = normalizedData.getSurface();
        var roverInformationList = normalizedData.getRoverInformationList();

        assertEquals(5, surface.width());
        assertEquals(6, surface.height());

        assertEquals(1, roverInformationList.get(0).getInitialPosition().x());
        assertEquals(2, roverInformationList.get(0).getInitialPosition().y());
        assertEquals(Direction.N, roverInformationList.get(0).getInitialPosition().direction());
        assertEquals(9, roverInformationList.get(0).getInstructions().size());

        assertEquals(3, roverInformationList.get(1).getInitialPosition().x());
        assertEquals(3, roverInformationList.get(1).getInitialPosition().y());
        assertEquals(Direction.E, roverInformationList.get(1).getInitialPosition().direction());
        assertEquals(10, roverInformationList.get(1).getInstructions().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"56", "5", "5 a", "a 5"})
    @DisplayName("Should return an error if the surface is invalid")
    void shouldReturnErrorIfSurfaceInvalid(String surface) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(surface);
        lines.add("1 2 N");
        lines.add("LMLMLMLMM");

        Error error = assertThrows(Error.class, () -> Normalize.normalizeData(lines));

        assertEquals("Invalid surface: " + surface, error.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a 2 N", "1 a N", "1 2N", "1 2 3", "12N", "12 N", "1 2 X"})
    @DisplayName("Should return an error if the initial position is invalid")
    void shouldReturnErrorIfInitialPositionInvalid(String initialPosition) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("5 5");
        lines.add(initialPosition);
        lines.add("LMLMLMLMM");

        Error error = assertThrows(Error.class, () -> Normalize.normalizeData(lines));

        assertEquals("Invalid initial position: " + initialPosition, error.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"LM M", "MM L", "ABC", "123"})
    @DisplayName("Should return an error if the instruction is invalid")
    void shouldReturnErrorIfInstructionInvalid(String instruction) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("5 5");
        lines.add("1 2 N");
        lines.add(instruction);

        Error error = assertThrows(Error.class, () -> Normalize.normalizeData(lines));

        assertEquals("Invalid instructions: " + instruction, error.getMessage());
    }
}
