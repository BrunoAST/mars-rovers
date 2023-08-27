package mars.rover;

import mars.rover.Normalizer.Information;

public class CommandRunner {
    public static void execute(Information data) {
        var invoker = new Invoker();

        for (var i = 0; i < data.getRoverInformationList().size(); i++) {
            invoker.addRover(data.getRoverInformationList().get(i), data.getSurface());
            invoker.sendInstructions(data.getRoverInformationList().get(i).instructions());
            invoker.move(i);
        }

        System.out.println(invoker.reportLocation());
    }
}
