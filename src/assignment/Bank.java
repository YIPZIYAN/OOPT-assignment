/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

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

    @Override
    public String toString() {
        return "Transaction History\n"
                + "===================\n"
                + "Basic       : RM " + String.format("%.2f", basic)
                + historyToString()
                + "Bank Amount : RM " + String.format("%.2f", amount);

    }

}
