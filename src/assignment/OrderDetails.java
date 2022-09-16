package assignment;

/**
 *
 * @author Yip Zi Yan
 */
public class OrderDetails {

    private Menu orderList;
    private int quantity;
    private static double subtotal = 0;

    public OrderDetails() {
    }

    public OrderDetails(Menu orderList, int quantity) {
        this.orderList = orderList;
        this.quantity = quantity;
        subtotal += orderList.price * quantity;
    }

    public Menu getOrderList() {
        return orderList;
    }

    public int getQuantity() {
        return quantity;
    }

    public static double getSubtotal() {
        return subtotal;
    }

    public void setOrderList(Menu orderList) {
        this.orderList = orderList;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void setSubtotal(double subtotal) {
        OrderDetails.subtotal = subtotal;
    }
    
    

    @Override
    public String toString() {
        return String.format("%s %6d RM %.2f", orderList.displayMenu(), quantity, orderList.price * quantity);
    }

}
