import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        SessionBuilder sessionBuilder = new SessionBuilder();
        SessionFactory sessionFactory = sessionBuilder.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();



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

        PurchaseList purchaseFirst = session.get(PurchaseList.class, new PurchaseKey("Кооскора Вениамин", "Java-разработчик"));
        System.out.println(purchaseFirst.getPrice());
        System.out.println(purchaseFirst.getSubscriptionDate());

        System.out.println("*****************************************");
        System.out.println("LinkedPurchaseList");
        System.out.println("*****************************************");

        linkedTableBuilder(session);



        transaction.commit();
        sessionFactory.close();

    }

    private static void linkedTableBuilder(Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);

        List<PurchaseList> listOfPurchases = session.createQuery(query).getResultList();
        List<LinkedPurchaseList> listOfLinkedPurchases = new ArrayList<>();

        listOfPurchases.forEach(onePurchase -> {
            String hqlStudent = "FROM " + Student.class.getSimpleName() + " WHERE name='" + onePurchase.getPurchaseKey().getStudentName() + "'";
            String hqlCourse = "FROM " + Course.class.getSimpleName() + " WHERE name='"+ onePurchase.getPurchaseKey().getCourseName() + "'";

            Student student = (Student) session.createQuery(hqlStudent).getSingleResult();
            Course course = (Course) session.createQuery(hqlCourse).getSingleResult();


            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
            linkedPurchase.setLinkedPurchaseKey(new LinkedPurchaseKey(student.getId(), course.getId()));

            linkedPurchase.setCourseName(onePurchase.getCourseName());
            linkedPurchase.setStudentName(onePurchase.getStudentName());

            linkedPurchase.setPrice(onePurchase.getPrice());
            linkedPurchase.setSubscriptionDate(onePurchase.getSubscriptionDate());

            session.save(linkedPurchase);
            listOfLinkedPurchases.add(linkedPurchase);
        });
    }
}
