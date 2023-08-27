package mars.rover;

public interface IVehicle {
    int getX();
    int getY();
    Direction getDirection();
    void move(Instructions instructions);
    String reportFinalPosition();
}
