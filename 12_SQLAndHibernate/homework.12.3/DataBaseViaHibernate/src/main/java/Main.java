import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.mapping.PrimaryKey;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<>();
        SessionBuilder builder = new SessionBuilder();
        SessionFactory sessionFactory = builder.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        BigInteger courseCount = (BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM skillbox.courses").uniqueResult();

        Course course1 = session.get(Course.class, 1);
        System.out.println(course1.getSubscriptions());

        // select * from subscriptions;
        Subscription subscription1 = session.get(Subscription.class, new SubscriptionKey(1, 2));
        String firstSubscription = subscription1.getSubscriptionDate().toString(); // in database: 2018-01-01 00:00:00.0

        // select * from subscriptions;
        Subscription subscriptionLast = session.get(Subscription.class, new SubscriptionKey(100, 19));
        String lastSubscription = subscriptionLast.getSubscriptionDate().toString(); // in database: 2018-07-19 00:00:00.0

        System.out.println("Даты первой и последней подписки в списке подписок базы данных:");
        System.out.println(firstSubscription);
        System.out.println(lastSubscription);

        // select * from purchaselist;
        PurchaseList purchase = session.get(PurchaseList.class, new PurchaseKey("Корбылев Якуб", "Мобильный разработчик PRO"));
        System.out.println(purchase.getPrice());
        System.out.println(purchase.getSubscriptionDate());

        //почему-то NullPointerException
        // выдает ошибку везде, где в названии есть дефис
        PurchaseList purchaseFirst = session.get(PurchaseList.class, new PurchaseKey("Амбражевич Порфирий", "Веб-разработчик с 0 до PRO"));
        System.out.println(purchaseFirst.getPrice());
        System.out.println(purchaseFirst.getSubscriptionDate());



        transaction.commit();
        sessionFactory.close();

    }
}
