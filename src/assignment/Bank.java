/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Yip Zi Yan
 */
public class Bank {

    private double basic;
    private static double amount;
    private static ArrayList<String> transHistory = new ArrayList<>();

    public Bank(double amount) {
        basic = amount;
        Bank.amount = amount;
    }

    public double getBasic() {
        return basic;
    }

    public static double getAmount() {
        return amount;
    }

    public void setBasic(double basic) {
        this.basic = basic;
    }

    public static void deduct(double deduction) {
        transHistory.add("(-) RM " + String.format("%7.2f", deduction));
        amount -= deduction;
    }

    public static void gain(double gain) {
        transHistory.add("(+) RM " + String.format("%7.2f", gain));
        amount += gain;
    }

    public static String historyToString() {
        String history = "\n";

        for (String i : transHistory) {
            history = history.concat(i + "\n");
        }

        return history;
    }

    public void printOut() {
        String filename = "src/TransactionHistory/"+"transaction_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss")) + ".txt";
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                try (FileWriter myWriter = new FileWriter(filename)) {
                    myWriter.write(toString());
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    @Override
    public String toString() {
        return "Transaction History\n"
                + "===================\n"
                + "Basic       : RM " + String.format("%.2f", basic)
                + historyToString()
                + "Bank Amount : RM " + String.format("%.2f", amount);
    }

}
