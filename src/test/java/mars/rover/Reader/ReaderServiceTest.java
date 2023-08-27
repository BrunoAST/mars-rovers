package mars.rover.Reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ReaderServiceTest {
    static Stream<Arguments> fileContentProvider() {
        return Stream.of(
                Arguments.of("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n", 5),
                Arguments.of("6 6\n1 1 W\nRRRRMMMM", 3)
        );
    }

    @Test
    @DisplayName("Should successfully read the content of a file")
    void shouldSuccessfullyReadTheContentOfAFile() throws IOException {
        Reader reader = new StringReader("5 5\n1 2 N\nLMLMLMLMM\n");

        var readerService = new ReaderService(reader);
        var result = readerService.readFile();

        assertEquals(3, result.size());
        assertEquals("5 5", result.get(0));
        assertEquals("1 2 N", result.get(1));
        assertEquals("LMLMLMLMM", result.get(2));
    }

    @Test
    @DisplayName("Should trim the spaces of the read file")
    void shouldTrimSpaces() throws IOException {
        Reader reader = new StringReader(    "5 5\n1 2 N\nLMLMLMLMM      \n");

        var readerService = new ReaderService(reader);
        var result = readerService.readFile();

        assertEquals("5 5", result.get(0));
        assertEquals("LMLMLMLMM", result.get(2));
    }

    @ParameterizedTest
    @MethodSource("fileContentProvider")
    @DisplayName("Should successfully read files with multiple rover information")
    void shouldSuccessfullyReadMultipleRoverInformation(String content, int expectedTotalLines) throws IOException {
        Reader reader = new StringReader(content);

        var readerService = new ReaderService(reader);
        var result = readerService.readFile();

        assertEquals(expectedTotalLines, result.size());
    }

    @Test
    @DisplayName("Should return an error if the total number of lines of the file are less than 3")
    void shouldReturnErrorIfLinesAreLessThanThree() {
        Reader reader = new StringReader("5 5\n1 2 N\n");

        var readerService = new ReaderService(reader);

        Error error = assertThrows(Error.class, readerService::readFile);

        assertEquals("The file doesn't match the minimal number of lines (3)", error.getMessage());
    }

    @Test
    @DisplayName("Should return an error if the total number of lines of the file is an even number")
    void shouldReturnErrorIfLinesAreEven() {
        Reader reader = new StringReader("1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n");

        var readerService = new ReaderService(reader);

        Error error = assertThrows(Error.class, readerService::readFile);

        assertEquals(
                "There is something missing; Expected an odd number of lines but got (4) instead",
                error.getMessage()
        );
    }
}
