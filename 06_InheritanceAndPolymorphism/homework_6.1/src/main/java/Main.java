public class Main
{
    public static void main(String[] args) {

        BankAccount husbandBank = new BankAccount();
        husbandBank.setOwnerName("Ferdinand of Aragon");
        husbandBank.put(100000.0);
        System.out.println(husbandBank);

        BankAccount wifeBank = new BankAccount();
        wifeBank.setOwnerName("Isabel of Castile");
        husbandBank.send(husbandBank, wifeBank, 23000.0);
        System.out.println();
        System.out.println(husbandBank);
        System.out.println();
        System.out.println(wifeBank);

        System.out.println();
        System.out.println("************************************************");
        System.out.println();

        CardAccount wifeCard = new CardAccount();
        wifeCard.setOwnerName("Isabel of Castile");
        wifeCard.put(45000.0);

        System.out.println();
        System.out.println(wifeCard);

        CardAccount husbandCard = new CardAccount();
        husbandCard.setOwnerName("Ferdinand of Aragon");
        wifeCard.send(wifeCard, husbandCard, 5000.0);

        System.out.println();
        System.out.println(wifeCard);
        System.out.println();
        System.out.println(husbandCard);

        System.out.println();
        System.out.println("************************************************");
        System.out.println();

        DepositAccount popeOfRome = new DepositAccount();
        popeOfRome.setOwnerName("Папа Римский");
        System.out.println(popeOfRome);
        popeOfRome.put(1000000.0);
        System.out.println();
        System.out.println(popeOfRome);


    }
}
