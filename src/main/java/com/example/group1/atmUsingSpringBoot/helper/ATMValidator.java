package com.example.group1.atmUsingSpringBoot.helper;

public class ATMValidator {
    public static boolean validateAmount(Double amount){
        if(amount < 0){
            return false;
        }
        return true;
    }

    public static boolean validateTransfer(String )
}
