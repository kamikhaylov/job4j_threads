package concurrent.search;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {
    private static final int SIZE_TO_SIMPLE_SEARCH = 10;
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public ParallelSearchIndex(T[] array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (from - to <= SIZE_TO_SIMPLE_SEARCH) {
            return simpleSearch(array, element);
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex<T> searchLeft = new ParallelSearchIndex(array, from, mid, element);
        ParallelSearchIndex<T> searchRight = new ParallelSearchIndex(array, mid + 1, to, element);
        searchLeft.fork();
        searchRight.fork();
        return Math.max(searchLeft.join(), searchRight.join());
    }

    public static <T> Integer search(T[] array, T element) {
        if (array.length == 0 || element == null) {
            return -1;
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex<T>(array, 0, array.length - 1, element));
    }

    private Integer simpleSearch(T[] array, T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
