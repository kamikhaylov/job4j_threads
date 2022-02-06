package concurrent;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOfferThenLimit() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);
        Thread first = new Thread(() -> {
            try {
                sbq.offer(1);
                sbq.poll();
                sbq.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(() -> {
            try {
                sbq.offer(2);
                sbq.offer(3);
                sbq.offer(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(2, is(sbq.getSize()));
    }

    @Test
    public void whenOffer6EndLimit4ThenPoll6() throws InterruptedException {
        SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(4);
        List<Integer> list = new ArrayList<>();
        Thread first = new Thread(() -> {
            for (int index = 0; index <  6; index++) {
                try {
                    sbq.offer(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread second = new Thread(() -> {
            for (int index = 0; index < 6; index++) {
                try {
                    list.add(sbq.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(list, is(List.of(0, 1, 2, 3, 4, 5)));
    }
}