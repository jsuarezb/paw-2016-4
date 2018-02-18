package ar.edu.itba.paw.models;

import java.util.List;

public class PagedResult<T> {

    private final List<T> results;
    private final int page;
    private final int pageSize;
    private final long total;

    public PagedResult(final List<T> results, final int page, final int pageSize, final long total) {
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
