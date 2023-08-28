package mars.rover.Contracts;

import mars.rover.Models.Direction;
import mars.rover.Models.Instruction;

public interface IVehicle {
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    Direction getDirection();
    void setDirection(Direction direction);
    ISurface getSurface();
    void move(Instruction instruction);
    String reportFinalPosition();
}
