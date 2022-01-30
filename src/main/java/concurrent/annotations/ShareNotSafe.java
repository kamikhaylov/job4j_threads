package concurrent.annotations;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        User user1 = User.of("name1");
        cache.add(user);
        cache.add(user1);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        for (User u : cache.findAll()) {
            System.out.println(u);
        }
    }
}