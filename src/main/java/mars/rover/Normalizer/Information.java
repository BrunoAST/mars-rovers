package mars.rover.Normalizer;

import mars.rover.Contracts.ISurface;

import java.util.ArrayList;

public class Information {
    private ISurface surface;
    private ArrayList<RoverInformation> roverInformationList = new ArrayList<>();

    public ISurface getSurface() {
        return surface;
    }

    public void setSurface(ISurface surface) {
        this.surface = surface;
    }

    public ArrayList<RoverInformation> getRoverInformationList() {
        return roverInformationList;
    }

    public void setRoverInformationList(ArrayList<RoverInformation> roverInformationList) {
        this.roverInformationList = roverInformationList;
    }
}
