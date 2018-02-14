package ar.edu.itba.paw.models;

import java.util.List;

public class PagedResult<T> {

    private final List<T> results;
    private final int page;
    private final int pageSize;
    private final long total;

    public PagedResult(List<T> results, int page, int pageSize, long total) {
        this.results = results;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

    public List<T> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }
}
