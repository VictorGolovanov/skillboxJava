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
    public void setUp() throws Exception {
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

    public synchronized void testTransaction(){
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            threads.add(new Thread(this::iterator));
        }

        for(Thread thread : threads){
            System.out.println("threadId " + thread.getId() + " *************************************************");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long sumAfter = bank.getSumAllAccounts();
        assertEquals(sumBefore, sumAfter);
    }
    public void iterator(){
        long sum = (long)(Math.random() * (MAX_TRANSACTION - MIN_TRANSACTION) + MIN_TRANSACTION);
        for(int i = 0; i < 100; i++){
            bank.transfer(accountVector.get((int) (Math.random() * 100)).getAccNumber(),
                          accountVector.get((int) (Math.random() * 100)).getAccNumber(),
                          sum);
            System.out.println("total sum => " + bank.getSumAllAccounts());
        }
    }
}
