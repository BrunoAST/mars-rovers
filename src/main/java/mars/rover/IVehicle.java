package mars.rover;

import mars.rover.Models.Direction;
import mars.rover.Models.Instructions;

public interface IVehicle {
    int getX();
    int getY();
    Direction getDirection();
    void move(Instructions instructions);
    String reportFinalPosition();
}
