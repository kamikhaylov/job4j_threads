package concurrent.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (i, b) -> {
            if (model.getVersion() != memory.get(model.getId()).getVersion()) {
                throw new OptimisticException("Version are not equal");
            }
            return new Base(model.getId(), model.getVersion() + 1);
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }
}