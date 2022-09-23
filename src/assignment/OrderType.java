package assignment;

public class OrderType {

    protected char ordertype;

    protected OrderType() {
    }

    protected OrderType(char orderType) {
        this.ordertype = orderType;
    }

    public char getOrdertype() {
        return ordertype;
    }

    public String displayOrderType() {
        return String.format("\n%15s %c", "Order Type: ", ordertype);
    }
}

//child class TAKEAWAY - FC
class Takeaway extends OrderType {

    protected String takeAwayID;
    protected int takeAwayNo;
    protected static double charges = 3.00;

    public Takeaway() {
        super('T');
        takeAwayNo++;
        this.takeAwayID = "TA" + takeAwayNo;
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType()
                + String.format("\n%15s %2d", "T/A No: ", takeAwayNo);
    }
}

//child class TABLE - FC
class Table extends OrderType {

    protected int tableNo;
    protected String tableId;

    public Table(int tableNo) {
        super('D');
        this.tableNo = tableNo;
        this.tableId = "TAB" + tableNo;
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType()
                + String.format("\n%15s %2d", "Table No: ", tableNo);
    }
}
