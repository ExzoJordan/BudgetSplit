package com.budgetsplit.budgetsplit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CustomerBudget {
    DecimalFormat df = new DecimalFormat("$#.##");
    Scanner scnr = new Scanner(System.in);

    double monthlyIncome; //Monthly income of user.
    double needsBudgetPercentage; //Percent of needs budget.
    double wantsBudgetPercentage; //Percent of wants budget.
    double savingsBudgetPercentage; //Percent of savings budget

    double needsBudget; //Amount of needs budget
    double wantsBudget; //Amount of wants budget
    double savingsBudget; //Amount of savings budget

    String name = "";

//SETTERS

    /**
     * Sets the name for the customer.
     *
     * @param name The name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the monthly income for customer
     *
     * @param monthlyIncome The monthly income of the customer.
     */
    public void setMonthlyIncome( double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Sets the needs budget amount.
     *
     * @param needsBudget
     */
    public void setNeedsBudget(double needsBudget) {
        this.needsBudget = needsBudget;
    }

    /**
     * Sets the wants budget amount.
     *
     * @param wantsBudget
     */
    public void setWantsBudget(double wantsBudget) {
        this.wantsBudget = wantsBudget;
    }

    /**
     * Sets the savings budget amount.
     *
     * @param savingsBudget
     */
    public void setSavingsBudget(double savingsBudget) {
        this.savingsBudget = savingsBudget;
    }


    /**
     * Sets the needs percentage budget.
     *
     * @param needsBudget The needs percentage budget.
     */
    public void setNeedsBudgetPercentage (double needsBudget) {
        this.needsBudgetPercentage = needsBudget;
    }

    /**
     * Sets the wants percentage budget.
     *
     * @param wantsBudget The wants percentage budget.
     */
    public void setWantsBudgetPercentage (double wantsBudget) {
        this.wantsBudgetPercentage = wantsBudget;
    }

    /**
     * Sets the savings percentage budget.
     *
     * @param savingsBudget The savings percentage budget
     */
    public void setSavingsBudgetPercentage (double savingsBudget) {
        this.savingsBudgetPercentage = savingsBudget;
    }

    public void createAccount () {

        //Get Customer Name.
        System.out.println("Please enter your name: ");
        this.setName(scnr.next());
        System.out.println("You're name is: " + this.getName());
    }
    /**
     * Allows user to set budget percentages
     */
    public void setBudget () {

        //Monthly Income Prompt
        System.out.print("Please Input your income for this month: ");

        //Store Monthly Income.
        this.setMonthlyIncome(scnr.nextDouble());

        System.out.println("Monthly income is:  " + df.format(this.getMonthlyIncome()) + "\n");

        //Set the budget. resets if the percentages doesn't add up to 1.
        while (this.checkBudget() == false) {

            //Resets budgets if done incorrectly.
            this.setNeedsBudgetPercentage(0);
            this.setWantsBudgetPercentage(0);
            this.setSavingsBudgetPercentage(0);

            System.out.println("Enter needs percentage");
            this.setNeedsBudgetPercentage(scnr.nextDouble()); //User inputs needs budget percentage

            System.out.println("Enter wants percentage");
            this.setWantsBudgetPercentage(scnr.nextDouble()); //User inputs wants budget percentage

            System.out.println("Enter savings percentage");
            this.setSavingsBudgetPercentage(scnr.nextDouble()); //User inputs savings budget percentage

            //Checks if the percentages adds up to 1
            if (this.checkBudget() == true) {
                System.out.println("Checking budget... \n");

                try {
                    Thread.sleep(2000);//Timer
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                System.out.println("Budget created!!");
            }
            else {
                System.out.println("Checking budget... \n");
                try {
                    Thread.sleep(1000);//Timer
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Budget Unsuccessful. Percentages must equal to 1 \n");
            }
        }

    }

//GETTERS

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the monthly income of customer
     *
     * @return The customers monthly income.
     */
    public double getMonthlyIncome() {
        return monthlyIncome;
    }
    /**
     * Gets the needs budget amount.
     *
     * @return needs budget amount.
     */
    public double getNeedsBudget() {
        return needsBudget;
    }

    /**
     * Gets the wants budget amount.
     *
     * @return wants budget amount.
     */
    public double getWantsBudget() {
        return wantsBudget;
    }


    /**
     * Gets the savings budget amount.
     *
     * @return savings budget amount.
     */
    public double getSavingsBudget() {
        return savingsBudget;
    }
//BOOLEAN

    /**
     * Checks If needs,wants, and savings percentage adds to 1
     *
     * @return True if sum is equal to 1, False if not equal to 1
     */
    public boolean checkBudget() {

        double sum = (needsBudgetPercentage + wantsBudgetPercentage + savingsBudgetPercentage);

        return Math.abs(sum - 1) < 0.0001;
    }

//METHODS


    /**
     * This method Calculates the needs budget, wants budget, and savings budget
     * based on the monthly income and budget percentages.
     *
     */
    public void calculateBudget() {
        needsBudget = monthlyIncome * needsBudgetPercentage;
        wantsBudget = monthlyIncome * wantsBudgetPercentage;
        savingsBudget = monthlyIncome * savingsBudgetPercentage;

    }

//ToString

    /**
     * This method prints the needs, wants, and savings budget amounts.
     *
     */
    public String toString() {

        return "According to you're income and split budget percentages \n" +
                "Your income should be split as such \n" +
                "\n" +
                "Needs budget: " + df.format(needsBudget) + "\n" +
                "Wants budget: " + df.format(wantsBudget) + "\n" +
                "Savings budget: " + df.format(savingsBudget) + "\n";
    }

//Write Method

    /**
     * This method writes needs, wants ,and savings budget to Budget.txt File.
     *
     */
    public void writeBudget() {
        try  {
            PrintWriter writer = new PrintWriter("Budget.txt");

            writer.println(name);
            writer.println(needsBudget);
            writer.println(wantsBudget);
            writer.println(savingsBudget);

            System.out.println("Data saved");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Error writting file");
        }
    }

//Read Method

    /**
     * This method reads the Budget.txt file and prints out the budget.
     */
    public void readBudget() {
        try {
            Scanner read = new Scanner(new File("Budget.txt"));

            while (read.hasNextLine()) {
                name = read.nextLine();
                needsBudget = Double.parseDouble(read.nextLine());
                wantsBudget = Double.parseDouble(read.nextLine());
                savingsBudget = Double.parseDouble(read.nextLine());

                System.out.println(needsBudget);
                System.out.println(wantsBudget);
                System.out.println(savingsBudget);
            }
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




}
