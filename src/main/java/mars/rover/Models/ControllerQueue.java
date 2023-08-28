package mars.rover.Models;

import mars.rover.IVehicle;

import java.util.ArrayList;

public record ControllerQueue(IVehicle vehicle, ArrayList<Instruction> instructions) {
}
