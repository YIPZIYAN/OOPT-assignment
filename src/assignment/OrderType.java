package assignment;

public class OrderType {

    char ordertype;

    public void OrderType() {
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
    int takeAwayNo; 
    double charges; 
    String status; 
    
    public void Takeaway() {
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType() + 
                String.format("\n%15s %2d", "T/A No: ", takeAwayNo)+ 
                String.format("\n%15s %s", "Status: ", status);
    }
}

//child class TABLE - FC

class Table extends OrderType {
    int tableNo;
    String status; 

    public void Table(int tableNo) {
        this.tableNo = tableNo;
    }

    @Override
    public String displayOrderType() {
        return super.displayOrderType() + 
                String.format("\n%15s %2d", "Table No: ", tableNo)+ 
                String.format("\n%15s %s", "Status: ", status);
    }
}
