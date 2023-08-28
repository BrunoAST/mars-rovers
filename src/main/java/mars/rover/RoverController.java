package mars.rover;

import mars.rover.Contracts.ISurface;
import mars.rover.Contracts.IVehicle;
import mars.rover.Models.ControllerQueue;
import mars.rover.Models.Instruction;
import mars.rover.Normalizer.RoverInformation;

import java.util.ArrayList;

public class RoverController {
    private final ArrayList<ControllerQueue> roversQueue = new ArrayList<>();

    public void addRover(RoverInformation roverInformation, ArrayList<Instruction> instructions, ISurface surface) {
        var initialPosition = roverInformation.getInitialPosition();
        IVehicle rover = new Rover(initialPosition.x(), initialPosition.y(), initialPosition.direction(), surface);
        var item = new ControllerQueue(rover, instructions);
        roversQueue.add(item);
    }

    public void move() {
        for (var item : roversQueue) {
            for (var instruction : item.instructions()) {
                item.vehicle().move(instruction);
            }
        }
    }

    public String reportLocation() {
        StringBuilder message = new StringBuilder();

        for (var item : roversQueue) {
            message.append(item.vehicle().reportFinalPosition()).append("\n");
        }

        return message.toString();
    }
}
