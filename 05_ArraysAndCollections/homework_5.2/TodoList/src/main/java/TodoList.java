import java.util.ArrayList;

public class TodoList {

    ArrayList<String> todoList = new ArrayList<>();

    public void add(String todo) {
        // TODO: добавьте переданное дело в конец списка
        todoList.add(todo);
        System.out.println("Добавлено дело " + todo);

    }

    public void add(int index, String todo) {
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления

        // если указанный индекс не существует, то добавляем просто в конец списка

        if(index >= 0 && index < todoList.size())
        {
            todoList.add(index, todo);
        }
        else {
            todoList.add(todo);
        }
        System.out.println("Добавлено дело " + todo);
    }

    public void edit(String todo, int index) {
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
        if(index < todoList.size() && index >= 0)
        {
            String oldDeal = todoList.get(index);
            todoList.set((Math.abs(index)), todo);
            System.out.println("Дело " + oldDeal + "заменено на дело " + todo);
        }
        // если указанного индекса нет (в том числе и ввод отрициательного числа)
        else{
            System.out.println("У вас не записано дело № " + index + " чтобы его редактировать!");
        }
    }

    public void delete(int index) {
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела

        if(index < todoList.size() && index >= 0)
        {
            String oldDeal = todoList.get(index);
            todoList.remove(Math.abs(index));
            System.out.println("Дело " + oldDeal + "удалено");
        }
        // если указанного индекса нет
        else{
            System.out.println("У вас не записано дело № " + index + " чтобы его удалять!");
        }
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел

        // На случай, если команда LIST вызывается до того, как добавили что-то в список
        if (todoList.size() == 0) {
            System.out.println("Вы еще не добавили дела в список!");
        } else {
            // если дела все-таки есть.
            System.out.println("Список добавленных дел:");
            for(int i = 0; i < todoList.size(); i++)
            {
                System.out.println("\t" + i + " " + todoList.get(i));
            }
        }

        // сделал так, тесты проходит
        return todoList;
    }

    // метод отбрасывает от строки команду и индекс, оставляя только "чистое" дело
    // например ADD 12 to read Engels => to read Engels
    public StringBuffer stringConstructor(int index, String toCheck){
        //
        String[] array = toCheck.split(" ");
        StringBuffer ourDeal = new StringBuffer();
        for(int i = index; i < array.length; i++) // пропускаем команду и число
        {
            ourDeal.append(array[i] + " ");
        }
        return ourDeal;
    }

    // метод определяет, есть ли указание на индекс в команде
    // например: ADD to read Lenin => false; ADD 2 to read Lenin => true;
    public boolean isNumberInCommand(String toCheck)
    {
        boolean isNumber = false;
        String[] array = toCheck.split(" ");
        for(char number : array[1].toCharArray())
        {
            if(Character.isDigit(number))
            {
                isNumber = true;
            }
        }
        return isNumber;
    }
}