package ar.edu.itba.paw.models;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by agophurmuz on 7/14/16.
 */
@XmlRootElement
public class ResponseList {
    private List<String> list;

    public ResponseList(List<String> list) {
        this.list = list;
    }

    /* default */ ResponseList(){}

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> neighborhoodList) {
        this.list = neighborhoodList;
    }
}
