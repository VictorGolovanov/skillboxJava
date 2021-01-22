public class Main
{
    public static void main(String[] args) {
        BankAccount husbandBank = new BankAccount();
        BankAccount wifeBank = new BankAccount();

        husbandBank.put(10000.0);
        wifeBank.put(10000.0);
        System.out.println("На банковском счете мужа: " + husbandBank.getAmount());
        System.out.println("На банковском счете жены: " + wifeBank.getAmount());
        System.out.println();

        wifeBank.send(husbandBank, 1000.0);
        System.out.println("На банковском счете мужа: " + husbandBank.getAmount());
        System.out.println("На банковском счете жены: " + wifeBank.getAmount());
        System.out.println();

        husbandBank.send(wifeBank, 20000.0);
        System.out.println("На банковском счете мужа: " + husbandBank.getAmount());
        System.out.println("На банковском счете жены: " + wifeBank.getAmount());
        System.out.println();
        System.out.println();

        CardAccount husbandCard = new CardAccount();
        CardAccount wifeCard = new CardAccount();
        System.out.println("На счете мужа: " + husbandCard.getAmount());
        System.out.println("На счете жены: " + wifeCard.getAmount());
        husbandCard.put(12000.0);
        System.out.println();

        System.out.println("На счете мужа: " + husbandCard.getAmount());
        System.out.println("На счете жены: " + wifeCard.getAmount());
        husbandCard.send(wifeCard, 2000.0);
        System.out.println();

        System.out.println("На счете мужа: " + husbandCard.getAmount());
        System.out.println("На счете жены: " + wifeCard.getAmount());
        System.out.println();
        System.out.println();

        DepositAccount husbandDeposit = new DepositAccount();
        DepositAccount wifeDeposit = new DepositAccount();

        System.out.println("На депозите мужа: " + husbandDeposit.getAmount());
        System.out.println("На депозите жены: " + wifeDeposit.getAmount());
        husbandDeposit.put(345123.0);
        System.out.println();

        System.out.println("На депозите мужа: " + husbandDeposit.getAmount());
        System.out.println("На депозите жены: " + wifeDeposit.getAmount());
        husbandDeposit.send(wifeDeposit, 123456);
        System.out.println();

        System.out.println("На депозите мужа: " + husbandDeposit.getAmount());
        System.out.println("На депозите жены: " + wifeDeposit.getAmount());

    }
}
