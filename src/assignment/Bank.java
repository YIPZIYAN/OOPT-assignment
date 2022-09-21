/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author Yip Zi Yan
 */
public class Bank {

    private static double amount;

    public Bank(double amount) {
        Bank.amount = amount;
    }

    public static double getAmount() {
        return amount;
    }

    public static void deduct(double deduction) {
        amount -= deduction;
    }

    public static void gain(double gain) {
        amount += gain;
    }

    @Override
    public String toString() {
        return "Bank Amount : RM " + String.format("%.2f", amount);
    }

}
