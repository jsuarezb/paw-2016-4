package ar.edu.itba.paw.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RatingSummary {

    public static final List<Integer> RATING_VALUES = Arrays.asList(1, 2, 3, 4, 5);

    private Double average;

    private Map<Integer, Long> valuesCount;

    /* package */ RatingSummary() {}

    public RatingSummary(final Double average, final Map<Integer, Long> valuesCount) {
        this.average = average;
        this.valuesCount = valuesCount;
    }

    public Double getAverage() {
        return average;
    }

    public Map<Integer, Long> getValuesCount() {
        return valuesCount;
    }
}
