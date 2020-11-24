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

        //TODO реализуйте метод для построения строки в следующем виде
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

        /*month -= 1; // чтобы в Java корректно обрабатывались результаты.
        StringBuffer result = new StringBuffer();
        // посчитаем, сколько лет прошло с момента дня рождения
        Calendar currentDate = Calendar.getInstance(); // создадим текущую дату в календаре
        int currentYear = currentDate.get(Calendar.YEAR); // и получим int значение года
        int yearAfterBirthday = currentYear - year; // God, I have a deal. Let the others grow old, not me! (Joey, Friends)

        Date today = new Date(); // будем сравнивать наши результаты с днем сегодняшним
        Calendar birthday = new GregorianCalendar(year, month, day);
        Date birthdayDate = birthday.getTime();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);

        if (!birthdayDate.after(today)) {
            for (int i = 0; i <= yearAfterBirthday; i++) {
                if(birthdayDate.after(today)){
                    break;
                }
                result.append("\n" + i + " - " + simpleFormat.format(birthdayDate));
                birthday.add(Calendar.YEAR, 1);
                birthdayDate = birthday.getTime();
            }
        }
        return result.toString();*/
    }
}
