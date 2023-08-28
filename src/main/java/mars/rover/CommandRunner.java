package mars.rover;

import mars.rover.Normalizer.Information;

public class CommandRunner {
    public static void execute(Information data) {
        var roverController = new RoverController();

        for (var i = 0; i < data.getRoverInformationList().size(); i++) {
            roverController.addRover(
                    data.getRoverInformationList().get(i),
                    data.getRoverInformationList().get(i).getInstructions(),
                    data.getSurface()
            );
        }

        roverController.move();

        System.out.print(roverController.reportLocation());
    }
}
