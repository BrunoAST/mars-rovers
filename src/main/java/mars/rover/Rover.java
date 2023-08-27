package mars.rover;

public class Rover extends Vehicle {
    public Rover(int x, int y, Direction direction, ISurface surface) {
        super(x, y, direction, surface);
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
