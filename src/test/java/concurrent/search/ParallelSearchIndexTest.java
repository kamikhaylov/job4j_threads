package concurrent.search;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParallelSearchIndexTest {

    @Test
    public void whenArray9thenIndex() {
        Integer[] array = {5, 4, 3, 2, 1, 9, 8, 7, 6};
        Integer expected = ParallelSearchIndex.search(array, 9);
        assertThat(expected, is(5));
    }

    @Test
    public void whenArray12thenIndex() {
        Integer[] array = {5, 4, 3, 2, 1, 9, 8, 7, 6, 22, 33, 11};
        Integer expected = ParallelSearchIndex.search(array, 22);
        assertThat(expected, is(9));
    }

    @Test
    public void whenArray35thenIndex() {
        String[] array = {"5", "4", "3", "2", "1", "9", "8", "7", "6", "5",
                "10", "20", "30", "40", "50", "60", "70", "80", "90", "89",
                "99", "88", "77", "66", "55", "44", "33", "22", "11", "23",
                "15", "45", "35", "25", "20"};
        Integer expected = ParallelSearchIndex.search(array, "22");
        assertThat(expected, is(27));
    }
}