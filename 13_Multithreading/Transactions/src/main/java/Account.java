public class Account {

    private boolean isBlocked;
    private long money;
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
        if(amount >= 0 && !this.isBlocked()){
            money += amount;
        }
        else if(this.isBlocked()){
            System.out.println("All operations with account => " + this.getAccNumber() + " are impossible!");
        }
        else {
            System.out.println("Don't have enough money");
        }
    }

    public void take(long amount){
        if(amount <= money && !this.isBlocked()){
            money -= amount;
        }
        else {
            System.out.println("Operation is impossible!");
        }
    }

}
