package ar.edu.itba.paw.webapp.params;

/**
 * Created by alebian on 12/8/16.
 */
public enum PaginationHelper {
    INSTANCE;

    public static final Integer DEFAULT_PER_PAGE = 20;
    public static final Integer DEFAULT_PAGE = 0;

    public Integer page(final Integer pageParam) {
        if (pageParam == null || pageParam <= 0) {
            return DEFAULT_PAGE;
        }
        return pageParam -1;
    }

    public Integer perPage(final Integer perPage) {
        if (perPage == null || perPage <= 0) {
            return DEFAULT_PER_PAGE;
        }
        return perPage;
    }

}
