package mars.rover;

import mars.rover.Models.Instructions;
import mars.rover.Normalizer.RoverInformation;

import java.util.ArrayList;

public class Invoker {
    private ArrayList<Rover> rovers = new ArrayList<>();
    private ArrayList<String[]> instructions = new ArrayList<>();

    public void addRover(RoverInformation roverInformation, ISurface surface) {
        var initialPosition = roverInformation.initialPosition();
        rovers.add(new Rover(initialPosition.x(), initialPosition.y(), initialPosition.direction(), surface));
    }

    public void sendInstructions(String[] instructions) {
        this.instructions.add(instructions);
    }

    public void move(int currentRover) {
        for (var instruction : instructions.get(currentRover)) {
            rovers.get(currentRover).move(Instructions.valueOf(instruction));
        }
    }

    public String reportLocation() {
        StringBuilder message = new StringBuilder();

        for (var rover : rovers) {
            message.append(rover.reportFinalPosition()).append("\n");
        }

        return message.toString();
    }
}
