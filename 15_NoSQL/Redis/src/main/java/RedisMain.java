import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RedisMain
{
    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 20 различных пользователей
    private static final int USERS = 20;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1000; // 1 секунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
        System.out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello!");

        RedisStorage redis = new RedisStorage();
        redis.init();

        //бесконечный цикл
        for (;;) {
            // Выполним 500 запросов
            for (int request = 0; request <= RPS; request++) {
                int user_id = new Random().nextInt(USERS);
                redis.logPageVisit(user_id);

                // выбираем кого показывать на главной странице из 20 пользователей
                int showRegularUserId = (int) (Math.random() * USERS);

                // в 1 из 10 случаев пользователь оплачивает показ без очереди
                if (Math.random() * 100 > 10) {
                    String log = String.format("[%s] На главной странице показываем пользователя №" + showRegularUserId, DF.format(new Date()));
                    System.out.println(log);
                }
                else {
                    int paidId = (int) (Math.random() * USERS);
                    System.out.println("Пользователь №" + paidId + " оплатил показ ");
                    String log = String.format("[%s] На главной странице показываем пользователя №" + paidId, DF.format(new Date()));
                    System.out.println(log);}
                Thread.sleep(SLEEP);
            }
            redis.shutdown();
        }
    }
}
