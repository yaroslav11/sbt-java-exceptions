package ru.sbt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Terminal t = new TerminalImpl(new TerminalServer(), new PinValidator());
        Scanner in = new Scanner(System.in);
        System.out.println("Codes of operation");
        System.out.println("1-\tenterPin");
        System.out.println("2-\trequestBalance");
        System.out.println("3-\tdepositeMoney");
        System.out.println("4-\twithdrawMoney");
        System.out.println("Enter the operation by it's code");
        while (true) {
            int in_data = in.nextInt();
            switch (in_data){
                case 1:
                    System.out.println("Enter PIN:");
                    t.enterPin(in.next());
                    break;
                case 2:
                    System.out.println("You requested balance:");
                    System.out.println("\t"+t.requestBalance());
                    break;
                case 3:
                    System.out.println("You try to deposite money");
                    t.depositeMoney(in.nextDouble());
                    break;
                case 4:
                    System.out.println("You try to withdraw money");
                    t.withdrawMoney(in.nextDouble());
                    break;
                default:
                    System.out.println("No such operation");
                    break;
            }
            System.out.println("Operation succeded");
        }
    }
}
