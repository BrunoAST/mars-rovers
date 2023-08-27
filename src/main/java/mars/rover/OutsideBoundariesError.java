package mars.rover;

public class OutsideBoundariesError extends Error {
    public OutsideBoundariesError(ISurface surface) {
        super("Landing position outside of the surface's boundaries width: " + surface.width() + " height: " + surface.height());
    }
}
