package mars.rover;

import mars.rover.Models.Direction;
import mars.rover.Models.Instruction;
import mars.rover.Models.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {
    static Stream<Arguments> validCoordinatesProvider() {
        return Stream.of(
                Arguments.of("LMLMLMLMM", "1 2 N", "1 3 N"),
                Arguments.of("MMRMMRMRRM", "3 3 E", "5 1 E")
        );
    }

    static Stream<Arguments> invalidCoordinatesProvider() {
        return Stream.of(
                Arguments.of("MMMMM", "1 2 N", "1 5 N"),
                Arguments.of("LMMMM", "3 1 W", "3 0 S"),
                Arguments.of("RMMMM", "4 4 S", "0 4 W")
        );
    }

    @ParameterizedTest
    @MethodSource("validCoordinatesProvider")
    @DisplayName("Should successfully move and report final position")
    void shouldSuccessfullyMove(String instructions, String initialPosition, String expectedOutput) {
        String[] position = initialPosition.split(" ");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        Direction direction = Direction.valueOf(position[2]);

        IVehicle rover = new Rover(x, y, direction, new Plateau(5, 5));

        for (String coordinate : instructions.split("")) {
            rover.move(Instruction.valueOf(coordinate));
        }

        Assertions.assertEquals(expectedOutput, rover.reportFinalPosition());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1 2 N", "6 3 E"})
    @DisplayName("Should throw an error when the rover lands beyond the surface of the plateau")
    void shouldReturnErrorWhenLandingBeyondPlateauSurface(String initialPosition) {
        String[] position = initialPosition.split(" ");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        Direction direction = Direction.valueOf(position[2]);

        Error error = assertThrows(Error.class, () -> new Rover(x, y, direction, new Plateau(5, 5)));

        Assertions.assertEquals(
                "Landing position outside of the surface's boundaries width: 5 height: 5",
                error.getMessage()
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCoordinatesProvider")
    @DisplayName("Should not apply movements that go beyond the boundaries of the plateau")
    void shouldNotApplyMovementsBeyondPlateauSurface(String instructions, String initialPosition, String expectedOutput) {
        String[] position = initialPosition.split(" ");
        int x = Integer.parseInt(position[0]);
        int y = Integer.parseInt(position[1]);
        Direction direction = Direction.valueOf(position[2]);

        IVehicle rover = new Rover(x, y, direction, new Plateau(5, 5));

        for (String coordinate : instructions.split("")) {
            rover.move(Instruction.valueOf(coordinate));
        }

        Assertions.assertEquals(expectedOutput, rover.reportFinalPosition());
    }
}
