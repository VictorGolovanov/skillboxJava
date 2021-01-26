public class Main
{
    public static void main(String[] args) {

        BankAccount husbandBank = new BankAccount();
        husbandBank.setOwnerName("Ferdinand of Aragon");
        husbandBank.put(100000.0);
        System.out.println(husbandBank);
        BankAccount wifeBank = new BankAccount();
        wifeBank.setOwnerName("Isabel of Castile");
        wifeBank.put(100000.0);
        System.out.println(wifeBank);

        System.out.println();
        System.out.println(husbandBank.moneyAmount);

        DepositAccount husbandDeposit = new DepositAccount();
        husbandDeposit.setOwnerName("Ferdinand of Aragon");
        //husbandDeposit.put(123456.789);
        husbandDeposit.setMoneyAmount(5435100.0);
        System.out.println(husbandDeposit);
        System.out.println();
        DepositAccount wifeDeposit = new DepositAccount();
        wifeDeposit.setOwnerName("Isabel of Castile");
        husbandDeposit.send(husbandDeposit, wifeDeposit, 3000000.0);
        System.out.println(husbandDeposit);
        System.out.println();
        System.out.println(wifeDeposit);

        /*
        husbandBank.put(123456.0);

        System.out.println();
        System.out.println(husbandBank);
        System.out.println();
        husbandBank.send(husbandBank, wifeBank, 23456.0);
        System.out.println();
        System.out.println(husbandBank);
        System.out.println();
        System.out.println(wifeBank);

        CardAccount husbandCard = new CardAccount();
        CardAccount wifeCard = new CardAccount();
        husbandCard.put(10000.0);

        System.out.println();
        System.out.println(husbandCard);
        System.out.println();
        husbandCard.send(husbandCard, wifeCard, 1000.0);
        System.out.println();
        System.out.println(husbandCard);
        System.out.println();
        System.out.println(wifeCard);*/

    }
}
