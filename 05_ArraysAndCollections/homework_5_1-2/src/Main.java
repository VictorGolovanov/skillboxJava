public class Main {
    public static void main(String[] args) {

        // константы
        final int patients = 30;
        final float min = 32.0f; // минимально возможная температура согласно условия
        final float max = 40.0f; // максимально возможная температура согласно условия
        final float minHealth = 36.2f;
        final float maxHealth = 36.9f;

        // пока пустой массив из 30 элементов
        float[] temperature = new float[patients];

        //добавляем случайные элементы из диапазона в массив
        for(int i = 0; i < patients; i++)
        {
            // такой способ получения случайного числа в рамках диапазона честно списал в интернете :( только привел к float
            temperature[i] = (float) ((Math.random() * ((max - min) + 1f)) + min);
        }

        // создадим еще массив, который будем использовать для построения строки
        String[] tempString = new String[patients];
        // переведем массив float в массив String
        for(int i = 0; i < patients; i++)
        {
            tempString[i] = Float.toString(temperature[i]);
        }

        // посчитаем среднюю температуру
        // сначала сложим все температуры
        float sumTemperature = 0.0f;
        for(int i = 0; i < patients; i++)
        {
            sumTemperature += temperature[i];
        }

        // вычисление средней температуры по больнице
        float averageTemperature = sumTemperature / patients;

        // построение строки с перечислением температур
        StringBuffer listOfTemperatures = new StringBuffer();
        for(int i = 0; i < patients; i++)
        {
            listOfTemperatures.append(tempString[i] + " ");
        }

        // узнаем, сколько пациентов здоровы
        int healthCount = 0;
        for(int i = 0; i < patients; i++)
        {
            if(minHealth <= temperature[i] & temperature[i] <= maxHealth)
            {
                healthCount += 1;
            }
        }

        System.out.println("Температуры пациентов: " + listOfTemperatures);
        //System.out.println("Средняя температура: " + averageTemperature);
        System.out.printf("Средняя температура: %.02f \u2103\n", averageTemperature);
        System.out.println("Количество здоровых: " + healthCount);
    }
}
