import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private volatile boolean isBlocked;
    //private volatile long money;
    private volatile long money;
    private String accNumber;

    public Account(String accNumber, long money){
        this.accNumber = accNumber;
        this.money = money;
        isBlocked = false;
    }

    public synchronized long getMoney() {
        return money;
    }


    public String getAccNumber() {
        return accNumber;
    }


    public synchronized void blockAccount(){
        isBlocked = true;
    }

    public synchronized boolean isBlocked() {
        return isBlocked;
    }

    public synchronized void put(long amount){
        if(amount >= 0 && !isBlocked){
            //money.addAndGet(amount);
            money += amount;
        }
        if(isBlocked){
            System.out.println("All operations with account => " + this.getAccNumber() + " are impossible!");
        }
    }

    public synchronized void take(long amount){
        if(amount <= money && !isBlocked){
            //money.addAndGet(-amount);
            money -= amount;
        }
        if(isBlocked){
            System.out.println("All operations with account => " + this.getAccNumber() + " are impossible!");
        }
    }

}
