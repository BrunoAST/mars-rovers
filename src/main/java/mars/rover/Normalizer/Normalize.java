package mars.rover.Normalizer;

import mars.rover.CustomErrors.InvalidInitialPositionError;
import mars.rover.CustomErrors.InvalidInstructionsError;
import mars.rover.CustomErrors.InvalidSurfaceError;
import mars.rover.Models.Direction;
import mars.rover.ISurface;
import mars.rover.Models.Plateau;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Normalize {
    public static Information normalizeData(ArrayList<String> lines) {
        var information = new Information();
        var rovers = new ArrayList<RoverInformation>();

        var surface = extractSurface(lines.get(0));
        information.setSurface(surface);

        for (int i = 1; i < lines.size(); i++) {
            var initialPosition = extractRoverInitialPosition(lines.get(i));
            var instructions = extractInstructions(lines.get(i+1));
            rovers.add(new RoverInformation(initialPosition, instructions));
            i++;
        }

        information.setRoverInformationList(rovers);

        return information;
    }

    private static ISurface extractSurface(String value) {
        if (!isValidSurfaceFormat(value)) {
            throw new InvalidSurfaceError(value);
        }

        var content = value.split("\\s");
        var width = Integer.parseInt(content[0]);
        var height = Integer.parseInt((content[1]));

        return new Plateau(width, height);
    }

    private static InitialPosition extractRoverInitialPosition(String value) {
        if (!isValidInitialPositionFormat(value)) {
            throw new InvalidInitialPositionError(value);
        }

        var content = value.split("\\s");
        var x = Integer.parseInt(content[0]);
        var y = Integer.parseInt((content[1]));
        var direction = Direction.valueOf(content[2].toUpperCase());

        return new InitialPosition(x, y, direction);
    }

    private static String[] extractInstructions(String value) {
        if (!isValidInstructions(value)) {
            throw new InvalidInstructionsError(value);
        }

        return value.toUpperCase().split("");
    }

    private static boolean isValidSurfaceFormat(String value) {
        return findPattern("^\\d+\\s{1}\\d+$", value);
    }

    private static boolean isValidInitialPositionFormat(String value) {
        return findPattern("^\\d+\\s{1}\\d+\\s{1}[WESN]{1}$", value);
    }

    private static boolean isValidInstructions(String value) {
        return findPattern("^[LRM]+$", value);
    }

    private static boolean findPattern(String regex, String input) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }
}
