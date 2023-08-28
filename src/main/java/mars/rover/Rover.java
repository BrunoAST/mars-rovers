package mars.rover;

import mars.rover.CustomErrors.OutsideBoundariesError;
import mars.rover.Models.Direction;
import mars.rover.Models.Instruction;

public class Rover implements IVehicle {
    private int x;
    private int y;
    private Direction direction;
    private final ISurface surface;

    public Rover(int x, int y, Direction direction, ISurface surface) {
        if (x < 0 || y < 0 || x > surface.width() || y > surface.height()) {
            throw new OutsideBoundariesError(surface);
        }

        this.x = x;
        this.y = y;
        this.direction = direction;
        this.surface = surface;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public ISurface getSurface() {
        return surface;
    }

    @Override
    public void move(Instruction instruction) {
        switch (instruction) {
            case L -> turnLeft();
            case R -> turnRight();
            case M -> moveForward();
        }
    }

    @Override
    public String reportFinalPosition() {
        return getX() + " " + getY() + " " + getDirection().toString();
    }

    private void turnLeft() {
        switch (getDirection()) {
            case N -> setDirection(Direction.W);
            case S -> setDirection(Direction.E);
            case E -> setDirection(Direction.N);
            case W -> setDirection(Direction.S);
        }
    }

    private void turnRight() {
        switch (getDirection()) {
            case N -> setDirection(Direction.E);
            case S -> setDirection(Direction.W);
            case E -> setDirection(Direction.S);
            case W -> setDirection(Direction.N);
        }
    }

    private void moveForward() {
        int position;

        switch (getDirection()) {
            case N -> {
                position = getY() + 1;
                if (position <= getSurface().height()) {
                    setY(position);
                }
            }
            case S -> {
                position = getY() - 1;
                if (position >= 0) {
                    setY(position);
                }
            }
            case E -> {
                position = getX() + 1;
                if (position <= getSurface().height()) {
                    setX(position);
                }
            }
            case W -> {
                position = getX() - 1;
                if (position >= 0) {
                    setX(position);
                }
            }
        }
    }
}
