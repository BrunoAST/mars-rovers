package mars.rover.Models;

import mars.rover.Contracts.ISurface;

public record Plateau(int width, int height) implements ISurface {
}
