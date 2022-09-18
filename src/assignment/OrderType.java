package assignment;

public class OrderType {

    char ordertype;

    public void OrderType(char orderType) {
        this.ordertype= orderType;
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
    String takeAwayID;
    int takeAwayNo;
    double charges;    
    String status;
    

    public void Takeaway() {
        takeAwayNo++;
        this.takeAwayID= "TA"+takeAwayNo;
        this.charges= 3.00;
        status= "preparing";
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType()
                + String.format("\n%15s %2d", "T/A No: ", takeAwayNo)
                + String.format("\n%15s %s", "Status: ", status);
    }
}

//child class TABLE - FC
class Table extends OrderType {
    int tableNo;
    String tableId;
    String status;

    public void Table(int tableNo) {
        this.tableNo = tableNo;
        this.tableId = "TAB"+tableNo;
        status="available";
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType()
                + String.format("\n%15s %2d", "Table No: ", tableNo);
    }
}