package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */
@XmlRootElement
public class NeighborhoodList {
    public List<String> neighborhoodList;

    public NeighborhoodList(List<String> neighborhoodList) {
        this.neighborhoodList = neighborhoodList;
    }

    /* default */ NeighborhoodList(){}

    public List<String> getNeighborhoodList() {
        return neighborhoodList;
    }

    public void setNeighborhoodList(List<String> neighborhoodList) {
        this.neighborhoodList = neighborhoodList;
    }
}
