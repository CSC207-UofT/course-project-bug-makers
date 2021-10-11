package com.courseApp.driver.commands;
import java.util.Scanner;

public class regUserLogin extends command{

    public regUserLogin(){
        super(2, 0);
    }
    public void run() {
        Scanner scan = new Scanner (new File(""));
        Scanner keyboard = new Scanner (System.in);
        String user = scan.nextLine();
        String pass = scan.nextLine(); // looks at selected file in scan

        String inpUser = keyboard.nextLine();
        String inpPass = keyboard.nextLine(); // gets input from user

        if (inpUser.equals(user) && inpPass.equals(pass)) {
            System.out.print("Successful login");
        } else {
            System.out.print("Login failed. Please try again.");
        }
}
