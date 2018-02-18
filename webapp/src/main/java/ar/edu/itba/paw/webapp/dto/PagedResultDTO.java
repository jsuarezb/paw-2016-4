package ar.edu.itba.paw.webapp.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PagedResultDTO<T> {
    @XmlElement
    private List<T> results;

    @XmlElement
    private Integer page;

    @XmlElement
    private Integer pageSize;

    @XmlElement
    private Long total;

    public PagedResultDTO() {}

    public PagedResultDTO(final List<T> results, final Integer page, final Integer pageSize, final Long total) {
        this.results = results;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }
}
