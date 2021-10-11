package com.courseApp.driver.commands;
import java.util.Scanner;

public class registerUser extends command{

    public registerUser(){
        super(2,2);
    }

    public String[] run(){
        Scanner keyboard = new Scanner (System.in);
        String inpUser = keyboard.nextLine();
        String inpPass = keyboard.nextLine(); // gets input from user

        return new String[]{inpUser, inpPass};
    }
}
