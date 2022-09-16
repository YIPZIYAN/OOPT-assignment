//package assignment;
//
///**
// *
// * @author Yip Zi Yan
// */
//public class Payment {
//
//    private int receiptNo = 1000;
//    private double total;
//    private double discountRate;
//    private static final double tax = 1.06;
//
//    public Payment() {
//        ++receiptNo;
//    }
//
//    public Payment(double total, double discountRate) {
//        this.total = total;
//        this.discountRate = discountRate;
//        ++receiptNo;
//    }
//
//    public Payment(double total) {
//        this.total = total;
//        ++receiptNo;
//    }
//
//    public double calculateGrandTotal() {
//        return total * discountRate * tax;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Discount    RM%.2f\nTax    RM%.2f\nGrand Total   RM%.2f\n\n", total * discountRate, total * tax, grandTotal);
//    }
//
//}
//
//class Ewallet extends Payment {
//
//    private String ewalletType;
//    private String ewalletID;
//    private String reference;
//
//    public Ewallet() {
//    }
//
//    public Ewallet(String ewalletType, String ewalletID, String reference, double total, double discountRate) {
//        super(total, discountRate);
//        this.ewalletType = ewalletType;
//        this.ewalletID = ewalletID;
//        this.reference = reference;
//    }
//
//    public Ewallet(String ewalletType, String ewalletID, String reference, double total) {
//        super(total);
//        this.ewalletType = ewalletType;
//        this.ewalletID = ewalletID;
//        this.reference = reference;
//    }
//
//    public String getEwalletType() {
//        return ewalletType;
//    }
//
//    public String getEwalletID() {
//        return ewalletID;
//    }
//
//    public String getReference() {
//        return reference;
//    }
//
//    public void setEwalletType(String ewalletType) {
//        this.ewalletType = ewalletType;
//    }
//
//    public void setEwalletID(String ewalletID) {
//        this.ewalletID = ewalletID;
//    }
//
//    public void setReference(String reference) {
//        this.reference = reference;
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + String.format("Paid by %s-%s\nReference : %s", ewalletID, ewalletType, reference);
//    }
//
//}
//
//class Cash extends Payment {
//
//    private double cashReceive;
//
//    public Cash() {
//    }
//
//    public Cash(double cashReceive, double total, double discountRate) {
//        super(total, discountRate);
//        this.cashReceive = cashReceive;
//    }
//
//    public Cash(double cashReceive, double total) {
//        super(total);
//        this.cashReceive = cashReceive;
//    }
//
//    public double getCashReceive() {
//        return cashReceive;
//    }
//
//    public void setCashReceive(double cashReceive) {
//        this.cashReceive = cashReceive;
//    }
//
//    public double calculateChange() {
//        return cashReceive - super.calculateGrandTotal();
//    }
//
//    public boolean checkAmount() {
//        return cashReceive >= calculateGrandTotal();
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + String.format("Cash Received   RM.2f\nChange     RM.2f", cashReceive, calculateChange());
//    }
//
//}
