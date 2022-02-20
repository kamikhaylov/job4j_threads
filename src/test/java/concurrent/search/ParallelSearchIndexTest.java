package concurrent.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParallelSearchIndexTest {

    @Test
    public void whenList9Toindex() {
        List<Integer> list = List.of(5, 4, 3, 2, 1, 9, 8, 7, 6);
        Integer expected = ParallelSearchIndex.search(list, 9);
        assertThat(expected, is(5));
    }

    @Test
    public void whenList12Toindex() {
        List<Integer> list = List.of(5, 4, 3, 2, 1, 9, 8, 7, 6, 22, 33, 11);
        Integer expected = ParallelSearchIndex.search(list, 22);
        assertThat(expected, is(9));
    }
}