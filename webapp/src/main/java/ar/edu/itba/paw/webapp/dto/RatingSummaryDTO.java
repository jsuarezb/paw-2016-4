package ar.edu.itba.paw.webapp.dto;

import ar.edu.itba.paw.models.RatingSummary;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class RatingSummaryDTO {

    @XmlElement
    private Double average;

    @XmlElement
    private Map<Integer, Long> valuesCount;

    public RatingSummaryDTO() {}

    public RatingSummaryDTO(final RatingSummary summary) {
        this.average = summary.getAverage();
        this.valuesCount = summary.getValuesCount();
    }

}
