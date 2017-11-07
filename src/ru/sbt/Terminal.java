package ru.sbt;

public interface Terminal {
    boolean enterPin(String pass);

    double requestBalance();
    boolean depositeMoney(double amount);
    boolean withdrawMoney(double amount);
}
