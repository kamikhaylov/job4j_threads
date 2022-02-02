package concurrent.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.HashMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private HashMap<Integer, User> storage = new HashMap<>();

    public synchronized boolean add(User user) {
        return storage.put(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return storage.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User fromUser = storage.get(fromId);
        User toUser = storage.get(toId);
        if (fromUser != null && toUser != null && fromUser.getAmount() >= amount) {
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() - amount);
            return true;
        }
        return false;
    }
}