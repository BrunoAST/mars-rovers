package mars.rover.Normalizer;

import mars.rover.Models.Instruction;

import java.util.ArrayList;

public class RoverInformation {
    private final InitialPosition initialPosition;
    private final String[] instructions;

    public RoverInformation(InitialPosition initialPosition, String[] instructions) {
        this.initialPosition = initialPosition;
        this.instructions = instructions;
    }

    public InitialPosition getInitialPosition() {
        return initialPosition;
    }

    public ArrayList<Instruction> getInstructions() {
        var values = new ArrayList<Instruction>();

        for (var instruction : instructions) {
            values.add(Instruction.valueOf(instruction));
        }

        return values;
    }
}
