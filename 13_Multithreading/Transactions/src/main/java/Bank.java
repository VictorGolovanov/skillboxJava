import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

    private ConcurrentHashMap<String, Account> accounts;
    private final Random random = new Random();
    private final long fraudLimit = 50000L;
    private String name;

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean(); // :)
    }

    public Bank(String name){
        accounts = new ConcurrentHashMap<>();
        this.name = name;
    }

    /**
     *  Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if(fromAccountNum.equals(toAccountNum)){
            System.out.println("Недопустимая операция: указан один и тот же счет!");
            return;
        }

        synchronized (accounts.get(fromAccountNum)){
            synchronized (accounts.get(toAccountNum)){
                if(amount >= 0 && !accounts.get(fromAccountNum).isBlocked() && !accounts.get(toAccountNum).isBlocked()){
                    if(accounts.get(fromAccountNum).getMoney() >= amount){
                        // с одного аккаунта снимаем сумму
                        accounts.get(fromAccountNum).take(amount);
                        // на другой эту сумму кладем
                        accounts.get(toAccountNum).put(amount);

                        System.out.println(fromAccountNum + " остаток на счете: " + this.getBalance(fromAccountNum));
                        System.out.println(toAccountNum + " остаток на счете: " + this.getBalance(toAccountNum));

                        if(amount >= fraudLimit){
                            try {
                                boolean isFraud = isFraud(fromAccountNum, toAccountNum, amount);
                                if(isFraud){
                                    accounts.get(fromAccountNum).blockAccount();
                                    accounts.get(toAccountNum).blockAccount();
                                    System.out.println("Заблокирован аккаунт: " + accounts.get(fromAccountNum).getAccNumber());
                                    System.out.println("Заблокирован аккаунт: " + accounts.get(toAccountNum).getAccNumber());
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public long getBalance(String accountNum) {
        return this.accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        return this.accounts.values().stream().mapToLong(Account::getMoney).sum();
    }

    public String getBankName() {
        return name;
    }

    public void addAccount(String accNumber, Account account){
        accounts.put(accNumber, account);
    }

    public void getAccNumbers(){
        Set<String> keys = accounts.keySet();
        for(String key : keys){
            System.out.println(key);
        }
    }

}
