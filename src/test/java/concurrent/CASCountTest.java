package concurrent;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CASCountTest {
    @Test
    public void whenIncrementOneThenGetOne() {
        CASCount count = new CASCount();
        count.increment();
        assertThat(count.get(), is(1));
    }

    @Test
    public void whenIncrementTwoThenGetTwo() {
        CASCount count = new CASCount();
        count.increment();
        count.increment();
        assertThat(count.get(), is(2));
    }

    @Test
    public void whenThreadTwoThenGetTwo() throws InterruptedException {
        CASCount count = new CASCount();
        count.increment();
        Thread thread = new Thread(() -> {
            count.increment();
        });
        thread.start();
        thread.join();
        assertThat(count.get(), is(2));
    }
}