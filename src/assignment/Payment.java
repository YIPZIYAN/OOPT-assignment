package assignment;

/**
 *
 * @author Yip Zi Yan
 */
public abstract class Payment {

    protected int receiptNo = 1000;
    protected double total;
    protected double discountRate;
    protected final static double tax = 1.06;

    protected Payment() {
    }

    protected Payment(double total, double discountRate) {
        this.total = total;
        this.discountRate = discountRate;
        ++receiptNo;
    }

    protected Payment(double total) {
        this.total = total;
        ++receiptNo;
    }

    public abstract double calculateGrandTotal();

    @Override
    public String toString() {
        return String.format("Discount    RM%.2f\nTax    RM%.2f\nGrand Total   RM%.2f\n\n", total * discountRate, total * tax, calculateGrandTotal());
    }

}

class Ewallet extends Payment {

    private String ewalletType;
    private String ewalletID;
    private String reference;

    public Ewallet() {
    }

    public Ewallet(String ewalletType, String ewalletID, String reference, double total, double discountRate) {
        super(total, discountRate);
        this.ewalletType = ewalletType;
        this.ewalletID = ewalletID;
        this.reference = reference;
    }

    public Ewallet(String ewalletType, String ewalletID, String reference, double total) {
        super(total);
        this.ewalletType = ewalletType;
        this.ewalletID = ewalletID;
        this.reference = reference;
    }

    public String getEwalletType() {
        return ewalletType;
    }

    public String getEwalletID() {
        return ewalletID;
    }

    public String getReference() {
        return reference;
    }

    public void setEwalletType(String ewalletType) {
        this.ewalletType = ewalletType;
    }

    public void setEwalletID(String ewalletID) {
        this.ewalletID = ewalletID;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public double calculateGrandTotal() {
        return super.total * super.discountRate * super.tax;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Paid by %s-%s\nReference : %s", ewalletID, ewalletType, reference);
    }

}

class Cash extends Payment {

    private double cashReceive;
    private double change;

    public Cash() {
    }

    public Cash(double cashReceive, double total, double discountRate) {
        super(total, discountRate);
        this.cashReceive = cashReceive;
    }

    public Cash(double cashReceive, double total) {
        super(total);
        this.cashReceive = cashReceive;
    }

    public double getCashReceive() {
        return cashReceive;
    }

    public double getChange() {
        return change;
    }

    public void setCashReceive(double cashReceive) {
        this.cashReceive = cashReceive;
    }

    public void setChange(double change) {
        this.change = change;
    }

    @Override
    public double calculateGrandTotal() {
        double grandTotal = super.total * super.discountRate * super.tax;
        change = cashReceive - grandTotal;
        return grandTotal;
    }

    public boolean checkAmount() {
        return cashReceive >= calculateGrandTotal();
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Cash Received   RM.2f\nChange     RM.2f", cashReceive, change);
    }

}
