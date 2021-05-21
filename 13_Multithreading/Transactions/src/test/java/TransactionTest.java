import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Vector;

public class TransactionTest extends TestCase
{
    private Bank bank;
    private Vector<Account> accountVector;
    private long sumBefore;

    private final long MAX_TRANSACTION = 60000;
    private final long MIN_TRANSACTION = 1000;

    @Override
    public void setUp() {
        bank = new Bank("Popular Bank");

        accountVector = new Vector<>();
        for(int i = 1; i <= 100; i++){
            String name = Integer.toString(i) + i + i + i;
            Account account = new Account(name, 123456);
            accountVector.add(account);
        }
        accountVector.forEach(account -> bank.addAccount(account.getAccNumber(), account));
        sumBefore = bank.getSumAllAccounts();
    }

    public void testRandomTransaction(){

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            threads.add(new Thread(this::iterator));
        }

        for(Thread thread : threads){
            thread.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("***************************************************************************");
            System.out.println("!!!!!!!!!!!!! threadName after start " + thread.getName() + " **************************************");
            System.out.println("***************************************************************************");

            try {
                thread.join();
                System.out.println("***************************************************************************");
                System.out.println("!!!!!!!!!!!!! threadName after join " + thread.getName() + " ***************************************");
                System.out.println("***************************************************************************");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long sumAfter = bank.getSumAllAccounts();
        assertEquals(sumBefore, sumAfter);
    }

    public void testTransactionWithTheSameAccounts(){
        Thread thread1 = new Thread(() ->{
            for(int i = 0; i <= 100; i++){
                if(i != 100){
                    long sum = 50001;
                    bank.transfer(accountVector.get(1).getAccNumber(), //i
                            accountVector.get(2).getAccNumber(),  // i+1
                            sum);
                    System.out.println("Transaction sum " + sum + " RUB **** Operation: " + i + " total sum => " + bank.getSumAllAccounts());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i <= 100; i++){
                if(i != 100){
                    long sum = 50001;
                    bank.transfer(accountVector.get(2).getAccNumber(), //i+1
                            accountVector.get(1).getAccNumber(), //i
                            sum);
                    System.out.println("Transaction sum " + sum + " RUB **** Operation: " + i + " total sum => " + bank.getSumAllAccounts());
                }
            }
        });

        thread1.start();
        System.out.println(thread1.getName() + " started");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
        try {
            System.out.println(thread2.getName() + " started");
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long sumAfter = bank.getSumAllAccounts();
        assertEquals(sumBefore, sumAfter);
    }

    public void iterator(){
        for(int i = 0; i < 100; i++){
            long sum = (long)(Math.random() * (MAX_TRANSACTION - MIN_TRANSACTION) + MIN_TRANSACTION);
            Account from = accountVector.get((int) (Math.random() * 100));
            Account to = accountVector.get((int) (Math.random() * 100));
            bank.transfer(from.getAccNumber(),
                          to.getAccNumber(),
                          sum);
            System.out.println("Accounts: " + from.getAccNumber() + " " +  to.getAccNumber() + " *** Operation: " + i + " amount " + sum +  " total sum => " + bank.getSumAllAccounts());
        }
    }
}
