package concurrent.pools;

import org.junit.Test;
import java.util.concurrent.ExecutionException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {
    @Test
    public void whenMatrixThenSum() {
        int[][] matrix = {{1, 1, 1},
                          {2, 2, 2},
                          {3, 4, 5}};
        RolColSum.Sums[] expected = {
                new RolColSum.Sums(3, 6),
                new RolColSum.Sums(6, 7),
                new RolColSum.Sums(12, 8)
        };
        assertThat(expected, is(RolColSum.sum(matrix)));
    }

    @Test
    public void whenMatrixThenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 1, 1},
                          {2, 2, 2},
                          {3, 4, 5}};
        RolColSum.Sums[] expected = {
                new RolColSum.Sums(3, 6),
                new RolColSum.Sums(6, 7),
                new RolColSum.Sums(12, 8)
        };
        assertThat(expected, is(RolColSum.asyncSum(matrix)));
    }
}