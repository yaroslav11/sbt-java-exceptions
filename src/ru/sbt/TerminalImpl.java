package ru.sbt;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isPin = false;
    private int failedAttemptsNumber =0;
    private long lastAtemptTime;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public boolean enterPin(String pass_str) {
        try {
            checkEnterAvaliabiliry();
            isPin = false;
            boolean chekPinStatus = pinValidator.checkPin(pass_str);
            if (!chekPinStatus){
                failedAttemptsNumber += 1;
                lastAtemptTime = System.currentTimeMillis();
                throw new IllegalArgumentException("Invalid Pin");
            } else {
                failedAttemptsNumber = 0;
                isPin = true;
                return true;
            }
        }catch (NumberFormatException e){
            throw new NumberFormatException("Pin must be a numeric value");
        }
    }


    @Override
    public double requestBalance() {
        checkAccountAvaliabiliry();
        double balance;
        try {
            balance = server.getMoney();
        }catch (Throwable tr){
            throw new IllegalStateException("Server Error");
        }
        return balance;
    }

    @Override
    public boolean depositeMoney(double amount) {
        checkAccountAvaliabiliry();
        if (amount % 100 != 0){
            throw new IllegalArgumentException("Amount must be a multiple of 100");
        }
        boolean operationStatus = server.addMoney(amount);
        if (!operationStatus) throw new IllegalStateException("Server Error");

        return true;
    }

    @Override
    public boolean withdrawMoney(double amount) {
        checkAccountAvaliabiliry();
        if (amount % 100 != 0){
            throw new IllegalArgumentException("Amount must be a multiple of 100");
        }
        boolean operationStatus = server.takeMoney(amount);
        if (!operationStatus) throw new IllegalStateException("Server Error");

        return true;
    }

    // utility methods
    private void checkEnterAvaliabiliry() {
        if (failedAttemptsNumber < 3) return;
        long remainedTime = lastAtemptTime + 5*1000 - System.currentTimeMillis();
        if (remainedTime <= 0){
            failedAttemptsNumber = 0;
            return;
        } else {
            throw new IllegalArgumentException("Pin may be reentered in " +
                    String.valueOf(remainedTime/1000)+" seconds");
        }
    }

    private void checkAccountAvaliabiliry(){
        if (!isPin){
            throw new IllegalArgumentException("Pin must be entered first");
        }
    }

}
