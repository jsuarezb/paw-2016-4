package ar.edu.itba.paw.webapp.utils;

/**
 * Created by alebian on 12/9/16.
 */
public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }
}
