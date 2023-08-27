package mars.rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlateauTest {
    @ParameterizedTest
    @ValueSource(strings = {"5 5 ", "1 2", "8 6"})
    @DisplayName("Should initialize the plateau with the correct dimensions")
    void shouldInitializeThePlateauWithTheCorrectDimensions(String dimensions) {
        ISurface surface = new Plateau();

        String[] values = dimensions.split(" ");
        int width = Integer.parseInt(values[0]);
        int height = Integer.parseInt(values[1]);

        surface.initializeSurface(width, height);

        Assertions.assertEquals(surface.getWidth(), width);
        Assertions.assertEquals(surface.getHeight(), height);
    }
}