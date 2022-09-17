package assignment;

import java.util.Comparator;

/**
 *
 * @author Yip Zi Yan
 */
public class OrderDetails implements Comparable<OrderDetails>{

    private Menu orderList;
    private int quantity;

    public OrderDetails() {
    }

    public OrderDetails(Menu orderList, int quantity) {
        this.orderList = orderList;
        this.quantity = quantity;
    }

    public Menu getOrderList() {
        return orderList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderList(Menu orderList) {
        this.orderList = orderList;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return quantity * orderList.price;
    }
    

    public static Comparator<OrderDetails> Comparator = new Comparator<OrderDetails>() {
  
        // Comparing attributes of students
        public int compare(OrderDetails s1, OrderDetails s2) {
            String OrderDetails1= s1.getOrderList().itemID.toUpperCase();
            String OrderDetails2= s2.getOrderList().itemID.toUpperCase();
  
            // Returning in ascending order
            return OrderDetails1.compareTo(OrderDetails2);
        }
    };

    @Override
    public String toString() {
        return String.format("%s %6d RM %.2f", orderList.displayMenu(), quantity, orderList.price * quantity);
    }

    public String displaySameOrderDetails() {
        return String.format("%s %6d RM %.2f", orderList.displayMenuNoID(), quantity, orderList.price * quantity);
    }

    @Override
    public int compareTo(OrderDetails o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
