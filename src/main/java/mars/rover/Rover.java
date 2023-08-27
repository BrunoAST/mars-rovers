package mars.rover;

public class Rover extends Vehicle {
    public Rover(int x, int y, Direction direction) {
        super(x, y, direction);
    }

    @Override
    public void move(Coordinates coordinates) {
        switch (coordinates) {
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
        switch (getDirection()) {
            case N -> setY(getY() + 1);
            case S -> setY(getY() - 1);
            case E -> setX(getX() + 1);
            case W -> setX(getX() - 1);
        }
    }
}
