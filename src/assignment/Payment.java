package assignment;

/**
 *
 * @author Yip Zi Yan
 */
public abstract class Payment implements Charges {

    protected double grandTotal;
    protected double discountAmount;

    protected Payment() {
    }

    protected Payment(double grandTotal, double discountAmount) {
        this.grandTotal = grandTotal;
        this.discountAmount = discountAmount;
    }

    protected Payment(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public abstract void transaction(double receive);

    @Override
    public String toString() {
        return String.format("Discount    RM%.2f\nTax    RM%.2f\nGrand Total   RM%.2f\n\n", discountAmount, grandTotal * TAX, grandTotal);
    }

}

class Ewallet extends Payment {

    private String ewalletType;
    private String ewalletID;
    private String reference;

    public Ewallet() {
    }

    public Ewallet(String ewalletType, String ewalletID, String reference, double grandTotal, double discountAmount) {
        super(grandTotal, discountAmount);
        this.ewalletType = ewalletType;
        this.ewalletID = ewalletID;
        this.reference = reference;
    }

    public Ewallet(String ewalletType, String ewalletID, String reference, double grandTotal) {
        super(grandTotal);
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
    public void transaction(double receive) {
        Bank.gain(receive);  //add to bank
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

    public Cash(double cashReceive, double grandTotal, double discountAmount) {
        super(grandTotal, discountAmount);
        change = cashReceive - grandTotal;
        this.cashReceive = cashReceive;
    }

    public Cash(double cashReceive, double grandTotal) {
        super(grandTotal);
        change = cashReceive - grandTotal;
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
    public void transaction(double receive) {
        Bank.gain(receive);
        Bank.deduct(change);
    }

    public boolean checkAmount(double grandTotal) {
        return cashReceive >= grandTotal;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Cash Received   RM.2f\nChange     RM.2f", cashReceive, change);
    }

}