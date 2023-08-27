package mars.rover;

public class Rover implements IVehicle {
    private int x;
    private int y;
    private Direction direction;

    public Rover(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void move(Coordinates coordinates) {
        switch (coordinates) {
            case L -> turnLeft();
            case R -> turnRight();
            case M -> moveForward();
            default -> throw new Error("Invalid coordinate provided");
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String reportFinalPosition() {
        return x + " " + y + " " + direction.toString();
    }

    private void turnLeft() {
        switch (direction) {
            case N -> direction = Direction.W;
            case S -> direction = Direction.E;
            case E -> direction = Direction.N;
            case W -> direction = Direction.S;
            default -> throw new Error("Invalid direction provided");
        }
    }

    private void turnRight() {
        switch (direction) {
            case N -> direction = Direction.E;
            case S -> direction = Direction.W;
            case E -> direction = Direction.S;
            case W -> direction = Direction.N;
            default -> throw new Error("Invalid direction provided");
        }
    }

    private void moveForward() {
        switch (direction) {
            case N -> y++;
            case S -> y--;
            case E -> x++;
            case W -> x--;
            default -> throw new Error("Invalid direction provided");
        }
    }
}
