public class Account {

    private boolean isBlocked;
    private volatile long money;
    private String accNumber;

    public Account(String accNumber, long money){
        this.accNumber = accNumber;
        this.money = money;
        isBlocked = false;
    }

    public long getMoney() {
        return money;
    }


    public String getAccNumber() {
        return accNumber;
    }


    public void blockAccount(){
        isBlocked = true;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void put(long amount){
        if(amount >= 0 && !isBlocked){
            //money.addAndGet(amount);
            money += amount;
        }
        if(isBlocked){
            System.out.println("All operations with account => " + this.getAccNumber() + " are impossible!");
        }
    }

    public void take(long amount){
        if(amount <= money && !isBlocked){
            money -= amount;
        }
        if(isBlocked){
            System.out.println("All operations with account => " + this.getAccNumber() + " are impossible!");
        }
    }

}
