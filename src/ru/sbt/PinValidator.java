package ru.sbt;

public class PinValidator {
    public boolean checkPin (String pass_str) throws NumberFormatException{
        int pass_int = Integer.parseInt(pass_str);
        if (pass_int == 1234){
            return true;
        }else {
            return false;
        }
    }
}
