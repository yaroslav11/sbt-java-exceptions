package ru.sbt;

public class TerminalServer {
    private double money;

    public double getMoney() {
        try {
            return money;
        } catch (Throwable t){
            throw new IllegalStateException("Internal server error");
        }

    }


    public boolean addMoney(double money) {
        try {
            this.money += money;
            return true;
        } catch (Throwable t){
            throw new IllegalStateException("Internal server error");
        }

    }

    public boolean takeMoney(double money) {
        try {
            if (this.money < money) {
                throw new IllegalArgumentException("Insufficient funds on the account");
            }
            this.money -= money;
            return true;
        } catch (Throwable t){
            throw new IllegalStateException("Internal server error");
        }
    }
}
