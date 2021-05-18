import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        Bank bank = new Bank("Popular Bank");

        List<Account> accountList = new ArrayList<>();
        for(int i = 1; i <= 100; i++){
            String name = Integer.toString(i) + i + i + i;
            Account account = new Account(name, 123456);
            accountList.add(account);
        }

        //accountList.forEach(account -> System.out.println(account.getAccNumber()));
        accountList.forEach(account -> bank.addAccount(account.getAccNumber(), account));

        System.out.println(bank.getSumAllAccounts());

        bank.transfer("1111", "2222", 60000);


    }
}
