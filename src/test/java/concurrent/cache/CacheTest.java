package concurrent.cache;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CacheTest {
    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        assertThat(cache.add(new Base(1, 1)), is(true));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        model1.setName("1");
        Base model2 = new Base(1, 1);
        model2.setName("2");
        cache.add(model1);
        assertThat(cache.update(model2), is(true));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateThenException() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Base model2 = new Base(1, 2);
        cache.add(model1);
        cache.update(model2);
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Base model2 = new Base(1, 1);
        cache.add(model1);
        cache.delete(model1);
        assertThat(cache.update(model2), is(false));
    }
}