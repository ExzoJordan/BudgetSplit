package com.budgetsplit.budgetsplit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

@SpringBootApplication
public class BudgetSplitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetSplitApplication.class, args);


        Scanner scnr = new Scanner(System.in);
        int numberOfCustomers = 10;//Limit on number of customers
        int customerID = -1;//Keeps track of customer ID's
        int choice = 10;//Choice corresponds with a number to use program.
        CustomerBudget[] Customer = new CustomerBudget[numberOfCustomers];

        DecimalFormat df = new DecimalFormat("$#.##");


        try {
            Scanner read = new Scanner(new File("Budget.txt"));
            int i = 0;
            double needs = 0, wants = 0, savings = 0;
            while (read.hasNextLine() && i < Customer.length) {


                Customer[i] = new CustomerBudget();
                String name = read.nextLine().trim();


                if (read.hasNextLine()) needs = Double.parseDouble(read.nextLine().trim());
                if (read.hasNextLine()) wants = Double.parseDouble(read.nextLine().trim());
                if (read.hasNextLine()) savings = Double.parseDouble(read.nextLine().trim());

                // Debug prints before setting values
                System.out.println("Customer " + (i + 1) + ":");
                System.out.println("Name: " + name);
                System.out.println("Needs Budget: " + needs);
                System.out.println("Wants Budget: " + wants);
                System.out.println("Savings Budget: " + savings);
                System.out.println("-----------------------------");


                Customer[i].setName(name);
                Customer[i].setNeedsBudget(needs);
                Customer[i].setWantsBudget(wants);
                Customer[i].setSavingsBudget(savings);

                customerID++;


                i++;
            }

            read.close(); // Close scanner
        } catch (FileNotFoundException e) {
            System.out.println("Error: Budget.txt file not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in Budget.txt.");
        }


        //Allows multiple people to create a budgeting account.
        while (choice != 0) {

            //Display menu
            System.out.println("Please enter a number: 1 - create a budget account \n"
                    + "                                2 - load an account \n"
                    + "                                3 - set a budget \n "
                    + "                                4 - Check budget ");

            choice = 10; //Resets the choice number to default.
            choice = scnr.nextInt();//Scans next choice number for program.

            switch (choice) {

                //Case 1 will allow you to create an account.
                case 1:

                    customerID++;

                    Customer[customerID] = new CustomerBudget();
                    System.out.println("Your customer ID is:" + customerID);
                    Customer[customerID].createAccount();

                    break;

                //Case 2 will load an account.
                case 2:

                    System.out.println("Please enter your customer ID: ");
                    customerID = scnr.nextInt();
                    System.out.println("Your name is: " + Customer[customerID].getName());

                    break;

                //Case 3 will set the budget.
                case 3:

                    //User sets the monthly income and budget percentages
                    Customer[customerID].setBudget();

                    //Calculate budget with monthly income
                    Customer[customerID].calculateBudget();

                    Customer[customerID].writeBudget();

                    //Print out budget
                    System.out.println(Customer.toString());


                    Customer[customerID].readBudget();

                    System.out.println("Current working directory: " + System.getProperty("user.dir"));

                    break;

                //Checks the budget on the account
                case 4:

                    System.out.println(Customer[customerID].toString());

                    break;

                default:

                    break;


            }
        }


        //Since I have multiple accounts ill write the files going through all the array list.
        //Array.length maybe would've been better but I'm taking a little break.
        try {

            PrintWriter writer = new PrintWriter("Budget.txt");

            for (int i = 0; i <= customerID; i++) {

                writer.println(Customer[i].getName());
                writer.println(Customer[i].getNeedsBudget());
                writer.println(Customer[i].getWantsBudget());
                writer.println(Customer[i].getSavingsBudget());

                System.out.println("Data saved");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writting file");
        }
    }
}
