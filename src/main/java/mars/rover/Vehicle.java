package mars.rover;

import mars.rover.CustomErrors.OutsideBoundariesError;
import mars.rover.Models.Direction;
import mars.rover.Models.Instructions;

public abstract class Vehicle {
    private int x;
    private int y;
    private Direction direction;
    private final ISurface surface;

    public Vehicle(int x, int y, Direction direction, ISurface surface) {
        if (x < 0 || y < 0 || x > surface.width() || y > surface.height()) {
            throw new OutsideBoundariesError(surface);
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.surface = surface;
    }

    abstract void move(Instructions instructions);
    abstract String reportFinalPosition();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ISurface getSurface() {
        return this.surface;
    }
}
