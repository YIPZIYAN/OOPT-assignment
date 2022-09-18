package assignment;

import java.time.LocalDate;
import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class Assignment {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        LoginInfo loginInfo = new LoginInfo();

        //Menu data - FC
        Menu[] menu = {new Food("Fry Noodle", "F001L", 10.00, 'L'), //arg (name, ID, price, size)
            new Food("Fry Noodle", "F001R", 8.00, 'R'), // R = Regular Size
            new Food("Fried Rice", "F002L", 12.00, 'L'), // L = large, 
            new Food("Fried Rice", "F002R", 10.00, 'R'),
            new Food("Soup Noodle", "F003L", 10.00, 'L'),
            new Food("Soup Noodle", "F003R", 8.00, 'R'),
            new Beverage("Tea", "D001I", 2.50, "Iced"),
            new Beverage("Tea", "D001H", 2.00, "Hot"),
            new Beverage("Coffee", "D002I", 2.50, "Iced"),
            new Beverage("Coffee", "D002H", 2.00, "Hot"),
            new Beverage("Milo", "D003I", 3.50, "Iced"),
            new Beverage("Milo", "D003H", 3.00, "Hot")
        };

        Member[] member = {new Member("Test", "012-1231123", LocalDate.of(2003, 12, 14)),
            new Member("Test2", "011-23222233", LocalDate.of(2003, 1, 14))};

        Voucher[] voucher = {new Voucher("ABC123", 40, 30, 10, LocalDate.of(2022, 12, 14)),
            new Voucher("HELLO", 40, 30, 10, LocalDate.of(2022, 9, 11))};

        ArrayList<OrderDetails> cart = new ArrayList<OrderDetails>();
        ArrayList<Order> orderRecord = new ArrayList<Order>();
        //order
        Order order = new Order();
        
        boolean doneOrder=false;
        int choice;
        do {
            clearScreen();
            System.out.println("Order");
            System.out.println("--------------------");
            System.out.println("1 - Start Order");
            System.out.println("2 - View Cart");
            System.out.println("3 - Go Back");
            System.out.print("Enter Selection > ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    startOrder(menu, cart);
                    break;
                case 2:
                    doneOrder = displayCart(order.getOrderID(), cart);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Selection!!");
            }
            if (doneOrder) {    //if an order had done, go out of loop
                break;  
            }
        } while (choice != 3);
        
        payment(cart,member,orderRecord);

    }

    public static void clearScreen() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
        }
    }

    public static void displayMember(final Member[] member) {
        System.out.println("Member List\n");
        System.out.println("ID     Name         Contact No    Birthday    Point");
        for (Member i : member) {
            System.out.println(i);
        }
    }

    public static void startOrder(final Menu[] menu, ArrayList<OrderDetails> cart) {
        boolean validItem = false;
        boolean isFood = false;
        boolean isBeverage = false;

        String itemID;
        char size;

        Menu orderItem = new Menu(); //get the ordered menu details
        int qtyOrder = 0;
        OrderDetails orderDetails = new OrderDetails(); //save to orderDetails and add to cart
        int sameItemIndex = 0;

        clearScreen();
        displayMenu(menu);
        do {

            System.out.print("Pick An Item      > ");
            itemID = scan.nextLine();

            for (Menu i : menu) {   //check item id available
                if (i.checkItem(itemID)) {
                    validItem = true;
                    if (i instanceof Food) {
                        isFood = true;
                        System.out.print("[L]arge/[R]egular > ");
                    } else {
                        isBeverage = true;
                        System.out.print("[I]ced/[H]ot      > ");
                    }
                    break;
                } else {
                    validItem = false;
                }
            }

            if (!validItem) {
                System.err.println("Invalid Input");
            }

        } while (!validItem);

        do { //ask user select variation ([L/R][I/H])
            validItem = false;
            size = scan.next().charAt(0);
            size = Character.toUpperCase(size); // change to upper case
            itemID = itemID.concat(Character.toString(size)); //get complete id

            if (isFood) {   //validation for size input
                validItem = Character.toString(size).matches("[LR]");
            } else {
                validItem = Character.toString(size).matches("[IH]");
            }

        } while (!validItem);

        boolean same = false;
        for (Menu i : menu) {   //get the selected manu object
            if (i.itemID.equals(itemID)) {
                orderItem = i;
                System.out.print("Enter Quantity    > ");
                qtyOrder = scan.nextInt();
                for (int j = 0; j < cart.size(); j++) {   //check same item in cart, if same just add qty
                    if (cart.get(j).getOrderList().equals(orderItem)) {
                        same = true;
                        sameItemIndex = j;
                        break;
                    }
                }
                if (!same) {
                    orderDetails = new OrderDetails(orderItem, qtyOrder);
                }

            }
        }

        System.out.printf("%s - %s [%d]\nAre You Sure ?    > ", orderItem.itemID, orderItem.itemName, qtyOrder);
        char choice = scan.next().charAt(0);
        if (Character.toUpperCase(choice) == 'Y') {
            if (same) {
                cart.get(sameItemIndex).setQuantity(cart.get(sameItemIndex).getQuantity() + qtyOrder);
            } else {
                cart.add(orderDetails);
            }
        }
    }

    public static void orderAnItem() {

    }

    public static boolean displayCart(int orderID, ArrayList<OrderDetails> cart) {
        clearScreen();
        Collections.sort(cart, OrderDetails.Comparator);
        double subtotal = 0;
        int sameCount = 0;
        System.out.println("Cart");
        System.out.println("Order ID : " + orderID);
        if (cart.isEmpty()) { //if empty
            System.err.println("Cart Is Empty!");
            System.out.println("Press Enter To Go Back...");
            scan.nextLine();
            return false;
        }
        System.out.println("==========================================================");
        System.out.printf("%-9s%-16s%-10s%-6s%-9s%s\n", "Item ID", "Item Name", "Price", "Type", "Quantity", "Subtotal");
        for (int i = 0; i < cart.size(); i++) {
            try {
                if (cart.get(i).getOrderList().itemName.equals(cart.get(i - 1).getOrderList().itemName)) {
                    System.out.println(cart.get(i).displaySameOrderDetails());
                } else {
                    System.out.println(cart.get(i));
                }
            } catch (Exception e) {
                System.out.println(cart.get(i));
            } finally {
                subtotal += cart.get(i).calculateSubtotal(); //add total
            }

        }
        System.out.println("==========================================================");
        System.out.printf("Total = RM %.2f\n", subtotal);
        System.out.print("Make Payment? [Y/N] > ");
        char cont = scan.next().charAt(0);
        switch (Character.toUpperCase(cont)) {
            case 'Y':
                return true;
            case 'N':
                return false;
            default:
                System.err.println("Invalid Input");
        }
        

    }

    public static void displayMenu(final Menu[] menu) {
        //display menu - FC
        String check1, check2;
        System.out.println("                   + MENU + ");
        System.out.println("===========================================\n");
        for (int i = 0; i < menu.length; i++) {
            if (i % 2 == 0) {
                System.out.println(menu[i].displayMenu());
            } else {
                check1 = menu[i].itemName;
                check2 = menu[i - 1].itemName;

                if (check1.compareTo(check2) == 0) {
                    System.out.println(menu[i].displayMenuNoID());
                    System.out.println("");
                } else {
                    System.out.println(menu[i].displayMenu());
                }
            }

        }
        System.out.println("===========================================\n");
    }

    public static void payment(ArrayList<OrderDetails> cart, Member[] member, ArrayList<Order> orderRecord) {
            Order order;


        
    }
// check voucher   
//            boolean haveVoucher = false;
//
//        System.out.print("Any Voucher? [Y/N] > ");
//        if (getYesNo()) {
//            System.out.print("Enter Voucher Code > ");
//            String voucherCode = scan.nextLine();
//
//            for (Voucher i : voucher) {
//                if (i.checkVoucher(voucherCode)) {
//                    haveVoucher = true;
//                    break;
//                }
//            }
//            
//            if (!haveVoucher) {     //if no such voucher
//                System.out.println("Invalid Voucher");
//            }
//        }
}
