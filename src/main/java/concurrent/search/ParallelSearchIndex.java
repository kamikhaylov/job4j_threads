package concurrent.search;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex extends RecursiveTask<Integer> {
    private static final int SIZE_TO_SIMPLE_SEARCH = 10;
    private final List list;
    private final int from;
    private final int to;
    private final Integer element;

    public ParallelSearchIndex(List list, int from, int to, Integer element) {
        this.list = list;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (from - to <= SIZE_TO_SIMPLE_SEARCH) {
            return simpleSearch(list, element);
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex searchLeft = new ParallelSearchIndex(list, from, mid, element);
        ParallelSearchIndex searchRight = new ParallelSearchIndex(list, mid + 1, to, element);
        searchLeft.fork();
        searchRight.fork();
        return Math.max(searchLeft.join(), searchRight.join());
    }

    public static Integer search(List list, Integer element) {
        if (list == null || element == null) {
            return -1;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex(list, 0, list.size() - 1, element));
    }

    private Integer simpleSearch(List list, Integer element) {
        return list.indexOf(element);
    }
}
