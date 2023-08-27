package mars.rover;

public class Plateau implements ISurface {
    private Surface surface;

    @Override
    public void initializeSurface(int width, int height) {
        surface = new Surface(width, height);
    }

    @Override
    public int getWidth() {
        return surface.getWidth();
    }

    @Override
    public int getHeight() {
        return surface.getHeight();
    }
}
