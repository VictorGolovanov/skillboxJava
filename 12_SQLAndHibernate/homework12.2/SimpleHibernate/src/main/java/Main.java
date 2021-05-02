import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {

        List<Course> courseList = new ArrayList<>();
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        SessionFactory sessionFactory = builder.getSessionFactory();

        Session session = sessionFactory.openSession();

        BigInteger courseCount = (BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM skillbox.courses").uniqueResult();

        int i = 1;
        while( i <= courseCount.intValue()){
            Course course = session.get(Course.class, i);
            courseList.add(course);
            i++;
        }

        sessionFactory.close(); // закрываем сессию здесь, а то в конце некрасивый красный шрифт глаза режет.

        for(Course oneCourse : courseList){
            System.out.println(oneCourse.getId() + " - " + oneCourse.getName() + " - " + oneCourse.getPrice() + " руб. - количество студентов: " + oneCourse.getStudentsCount());
        }
    }
}