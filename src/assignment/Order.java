package assignment;

/**
 *
 * @author Yip Zi Yan
 */
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Collections;

public class Order implements Charges {

    private int orderID;
    private LocalDate orderDate;
    private OrderType orderType;
    private double grandTotal;
    private ArrayList<OrderDetails> orderDetails;
    private Member memberDetails;
    private Employee empDetails;
    private static int totalOrder = 1000;

    public Order() {
    }

    public Order(OrderType orderType, Member memberDetails, Employee empDetails, ArrayList<OrderDetails> orderDetails) {  //memebr
        this.orderDate = LocalDate.now();
        this.orderType = orderType;
        this.memberDetails = memberDetails;
        this.empDetails = empDetails;
        this.orderDetails = orderDetails; //Collections.copy(this.orderDetails,orderDetails);
        totalOrder++;
        orderID += totalOrder;
    }

    public Order(OrderType orderType, Employee empDetails, ArrayList<OrderDetails> orderDetails) {  //non-member
        this.orderDate = LocalDate.now();
        this.orderType = orderType;
        this.empDetails = empDetails;
        this.orderDetails = orderDetails; //Collections.copy(this.orderDetails,orderDetails);
        totalOrder++;
        orderID += totalOrder;
    }

    public int getOrderID() {
        return orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public Member getMemberDetails() {
        return memberDetails;
    }

    public Employee getEmpDetails() {
        return empDetails;
    }

    public static int getTotalOrder() {
        return totalOrder;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setMemberDetails(Member memberDetails) {
        this.memberDetails = memberDetails;
    }

    public void setEmpDetails(Employee empDetails) {
        this.empDetails = empDetails;
    }

    public static void setTotalOrder(int totalOrder) {
        Order.totalOrder = totalOrder;
    }

    //subtotal
    public double calculateSubtotal(ArrayList<OrderDetails> orderDetails) {
        double subtotal = 0;
        for (OrderDetails i : orderDetails) {
            subtotal += i.getQuantity() * i.getOrderList().price;
        }
        return subtotal;
    }

    //total with tax and discount
    public double calculateGrandTotal(double subtotal, double discount) {
        if (orderType instanceof Takeaway) {
            this.grandTotal = subtotal * TAX * discount + Takeaway.charges;
            return subtotal * TAX * discount + Takeaway.charges;
        }
        this.grandTotal = subtotal * TAX * discount;
        return subtotal * TAX * discount;
    }

    //total with tax only
    public double calculateGrandTotal(double subtotal) {
        if (orderType instanceof Takeaway) {
            this.grandTotal = subtotal * TAX + Takeaway.charges;
            return subtotal * TAX + Takeaway.charges;
        }
        this.grandTotal = subtotal * TAX;
        return subtotal * TAX;
    }

    @Override
    public String toString() {  // to print out sales summary
        return String.format(
                "%04d  %10s %8s RM%10.2f", orderID, empDetails.getName(), orderType.getOrdertype(), grandTotal);
    }

}
