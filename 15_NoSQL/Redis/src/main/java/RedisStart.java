public class RedisStart
{
    public static void main(String[] args) throws InterruptedException {
        RedisStorage storage = new RedisStorage();
        storage.init();

        for (int i = 1; i <= 20; i++) {
            storage.singIn(i);
            Thread.sleep(200);
        }

        while (true) {
            if ((int) (Math.random() * 10) == 1)
                storage.pay((int) (Math.random() * 20) + 1);
            System.out.println("На главной странице показываем пользователя № " + storage.showedUser());
            Thread.sleep(1000);
        }
    }
}
