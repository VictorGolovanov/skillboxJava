import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 6;
        int month = 9;
        int year = 1995;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue

        StringBuffer result = new StringBuffer(); // сюда будем сохранять наш результат
        LocalDate birthday = LocalDate.of(year, month, day); // "нулевой" день рождения
        LocalDate today = LocalDate.now(); // сегодняшний день

        // формат вывода
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - E", new Locale("en"));

        int i = 0; // наш счетчик
        while(today.isAfter(birthday) | today.isEqual(birthday))
        {
            result.append("\n" + i + " - " + birthday.format(formatter));
            birthday = birthday.plusYears(1);
            i++;


        }
        return result.toString();
    }
}
