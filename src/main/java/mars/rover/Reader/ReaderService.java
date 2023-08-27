package mars.rover.Reader;

import mars.rover.CustomErrors.InvalidFileMinimumNumberOfLinesError;
import mars.rover.CustomErrors.InvalidFileTotalNumberOfLines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

public class ReaderService {
    private final Reader reader;

    public ReaderService(Reader reader) {
        this.reader = reader;
    }

    public ArrayList<String> readFile() throws IOException {
        var bufferedReader = new BufferedReader(reader);

        String line;
        var lines = new ArrayList<String>();

        while ((line = bufferedReader.readLine()) != null) {
            if (hasData(line)) {
                lines.add(line.trim());
            }
        }

        if (!hasMinimumNumberOfLines(lines)) {
            throw new InvalidFileMinimumNumberOfLinesError();
        }

        if (isValidFileStructure(lines)) {
            throw new InvalidFileTotalNumberOfLines(lines.size());
        }

        return lines;
    }

    /**
    * Given that a valid input must contain at least: surface dimensions, initial landing position and instructions
    **/
    private boolean hasMinimumNumberOfLines(ArrayList<String> lines) {
        return lines.size() >= 3;
    }

    /**
     * If the file has an even number of lines, it means some information is missing.
     * Given that: 1st line is the surface size and the next two must be the rover information,
     * meaning that surface + information for a rover (2) = will be always return an odd number of lines.
    * */
    private boolean isValidFileStructure(ArrayList<String> lines) {
        return lines.size() % 2 == 0;
    }

    private boolean hasData(String line) {
        return !Objects.equals(line.trim(), "");
    }
}
