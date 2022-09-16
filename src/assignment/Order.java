package assignment;

/**
 *
 * @author Yip Zi Yan
 */
import java.time.LocalDate;

public class Order {

    private int orderID = 1000;
    private LocalDate orderDate;
    private OrderType orderType;
    private OrderDetails orderDetails;
    private Member memberDetails;
    private Employee empDetails;
    private static int totalOrder;

    public Order() {
        totalOrder++;
        orderID += totalOrder;
    }

    public Order(OrderType orderType, Member memberDetails, Employee empDetails, OrderDetails orderDetails) {  //memebr
        this.orderDate = LocalDate.now();
        this.orderType = orderType;
        this.memberDetails = memberDetails;
        this.empDetails = empDetails;
        this.orderDetails = orderDetails;
        totalOrder++;
        orderID += totalOrder;
    }

    public Order(OrderType orderType, Employee empDetails, OrderDetails orderDetails) {    //non-member
        this.orderDate = LocalDate.now();
        this.orderType = orderType;
        this.empDetails = empDetails;
        this.orderDetails = orderDetails;
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

    public OrderDetails getOrderDetails() {
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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
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

    @Override
    public String toString() {
        return String.format("Order ID : %04d\nDate : %s\n%s\n%s\n", orderID, orderDate, empDetails, orderType, orderDetails);
    }

}
