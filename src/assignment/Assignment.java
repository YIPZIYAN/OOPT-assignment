package assignment;

import java.time.LocalDate;
import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class Assignment {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        
         //Employee ID and password - Vallerie
        HashMap<String, String> loginInfo = new HashMap<String, String>();
        loginInfo.put("A1001", "Admin1111");
        loginInfo.put("A1002", "Admin2222");
        loginInfo.put("A1003", "Staff3333");
        loginInfo.put("A1004", "Staff4444");
        loginInfo.put("A1005", "Staff5555");    
        
        //Employee Details
        Employee[] empDetails = {new Employee("ZANICE", 'F', "0123456789", "Admin1111", "Manager", 10000.00),
                                 new Employee("RYAN", 'M', "0178888888", "Admin2222", "Clerk", 3000.00),
                                 new Employee("XAVIER", 'M', "0138796454", "Staff3333", "Clerk", 3000.00),
                                 new Employee("WINSON", 'M', "0189764533", "Staff4444", "Clerk", 3000.00),
                                 new Employee("VANESSA", 'F', "0135437755", "Staff5555", "Clerk", 3000.00)};

        ArrayList<Takeaway> list = new ArrayList<Takeaway>();
        Table[] tableNo = {new Table(1), new Table(2), new Table(3), new Table(4), new Table(5),
            new Table(6), new Table(7), new Table(8), new Table(9), new Table(10)};

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
        Login login = new Login(loginInfo);
        
        
       

        boolean doneOrder = false;
        int choice = 0;
        boolean continueInput = true;
        do {
            clearScreen();
            System.out.println("Order");
            System.out.println("--------------------");
            System.out.println("1 - Start Order");
            System.out.println("2 - View Cart");
            System.out.println("3 - Go Back");
            System.out.print("Enter Selection > ");
            choice = getInput(choice);
            scan.nextLine();

            switch (choice) {
                case 1:
                    startOrder(menu, cart);
                    break;
                case 2:
                    doneOrder = displayCart(Order.getTotalOrder(), cart);
                    break;
                case 3:
                    continueInput = false;
                    break;
                default:
                    System.err.println("Invalid Selection!!");
                    syspause.oneSec();
            }
            if (doneOrder) {    //if an order had done, go out of loop
                continueInput = false;
            }

        } while (continueInput);

        if (doneOrder) {
            Order order = settingBeforePayment(tableNo, cart, member, emp, orderRecord); //get complete order
            payment(voucher);
        }

    }

    public static int getInput(int input) { //exception handling for int input
        try {
            input = scan.nextInt();
        } catch (Exception e) {
            return -1; //invalid
        }
        return input;
    }
    
    public static char getInput(char input) {  //exception handling for char input
        boolean continueInput = true;
        do {
            try {
                input = scan.next(".").charAt(0);
                continueInput = false;
            } catch (Exception e) { //invalid
                System.err.println("Invalid input.");
                scan.nextLine();
                System.out.println("Please Re-enter   > ");

            }
        } while (continueInput);
        return input;
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
        char size = 'x';

        Menu orderItem = new Menu(); //get the ordered menu details
        int qtyOrder = 0;
        OrderDetails orderDetails = new OrderDetails(); //save to orderDetails and add to cart
        int sameItemIndex = 0;

        clearScreen();
        displayMenu(menu);
        do {

            System.out.print("Pick An Item      > ");
            itemID = scan.nextLine();
            itemID = itemID.toUpperCase();

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
            size = getInput(size);
            size = Character.toUpperCase(size); // change to upper case
            itemID = itemID.concat(Character.toString(size)); //get complete id

            if (isFood) {   //validation for size input
                if (Character.toString(size).matches("[.LR.]")) {
                    validItem = true;
                } else {
                    validItem = false;
                    scan.nextLine();
                    System.err.println("Invalid Input.");
                    System.out.print("Please re-enter "
                            + "\n[L]arge/[R]egular > ");
                }
            } else {
                if (Character.toString(size).matches("[.IH.]")) {
                    validItem = true;
                } else {
                    validItem = false;
                    scan.nextLine();
                    System.err.println("Invalid Input.");
                    System.out.print("Please re-enter "
                            + "\n[I]ced/[H]ot > ");
                }
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

        char cont;
        do {
            System.out.print("Make Payment? [Y/N] > ");
            cont = scan.next().charAt(0);
            switch (Character.toUpperCase(cont)) {
                case 'Y':
                    return true;
                case 'N':
                    break;
                default:
                    System.err.println("Invalid Input");
            }
        } while (Character.toUpperCase(cont) != 'Y' || Character.toUpperCase(cont) != 'N');
        return false;
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

    public static Order settingBeforePayment(Table[] tableNo, ArrayList<OrderDetails> cart, Member[] member, Employee emp, ArrayList<Order> orderRecord) {
        OrderType orderType = new OrderType();
        Order order = new Order();
        System.out.print("[D]ine In/[T]ake away   > ");   //*need a looping or do it in another function
        char type = scan.next().charAt(0);
        switch (Character.toUpperCase(type)) {
            case 'D':
                System.out.print("Select A Table  > "); //validation needed
                int table = scan.nextInt();
                orderType = tableNo[table - 1];
                break;
            case 'T':
                orderType = new Takeaway();
                break;
            default:
                System.err.println("Invalid Input!");
        }

        boolean isMember = false;
        System.out.print("Is A Member? [Y/N] > ");    //*need a looping or do it in another function
        char memberChoice = scan.next().charAt(0);
        switch (Character.toUpperCase(memberChoice)) {
            case 'Y':
                isMember = true;
                break;
            case 'N':
                order = new Order(orderType, emp, cart);
                break;
            default:
                System.err.println("Invalid Input");
        }

        while (isMember) {
            scan.nextLine();    //buffer
            System.out.print("Enter Member ID > ");
            String memberID = scan.nextLine();
            for (Member i : member) {
                if (i.validateMember(memberID)) { //if same member ID
                    order = new Order(orderType, i, emp, cart); //create object with member
                    isMember = true;
                    break;
                }
                isMember = false;   //invalid memberID
            }
            if (isMember) {
                break;  //stop loop
            } else {
                System.err.println("No Such Member ID!");
                System.out.print("[T]ry Again/[C]ontinue Without Member> > ");
                char choice = scan.next().charAt(0);
                if (Character.toUpperCase(choice) == 'T') {
                    isMember = true;
                } else {
                    order = new Order(orderType, emp, cart); // create object with no member
                }
            }
        }

        return order;
    }

    public static void payment(final Voucher[] voucher) {

        boolean invalid;
        boolean haveVoucher;
        Voucher applyVoucher;
        do {
            invalid = false;
            haveVoucher = false;
            System.out.print("Any Voucher? [Y/N] > ");
            char voucherChoice = scan.next().charAt(0);
            switch (Character.toUpperCase(voucherChoice)) {
                case 'Y':
                    haveVoucher = true;
                    break;
                case 'N':
                    break;
                default:
                    invalid = true;
                    System.err.println("Invalid Input");
            }
        } while (invalid);

        while (haveVoucher) {
            scan.nextLine();    //buffer
            System.out.print("Enter Voucher Code > ");
            String voucherCode = scan.nextLine();
            for (Voucher i : voucher) {
                if (i.getVoucherCode().equals(voucherCode.toUpperCase())) { //if same voucher code
                    applyVoucher = i;   //get the voucher code
                    haveVoucher = true;
                    break;
                }
                haveVoucher = false;   //invalid voucher code
            }
            if (haveVoucher) {  //applied voucher
                break;  //stop loop
            } else {
                System.err.println("No Such Voucher Code!");
                System.out.print("[T]ry Again/[C]ontinue Without Voucher? > ");
                char choice = scan.next().charAt(0);
                if (Character.toUpperCase(choice) == 'T') {
                    haveVoucher = true;
                }
            }
        }

    }
}
