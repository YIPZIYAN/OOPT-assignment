package assignment;

import java.time.LocalDate;
import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.io.Console;

public class Assignment {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

//        QRcode qr;
//        qr = QRcode.displayQRcode();
//        System.out.println("enter smtg..");
//        scan.nextLine();
//        QRcode.closeQRcode(qr);
        //Employee Details - Vallerie
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

        Login login = new Login();

        Employee empInCharge;
        empInCharge = login.Login(empDetails);  //login and get emp in charge details
        int choice = 0;
        boolean valid;
        do {
            clearScreen();
            valid = true;
            System.out.println("\n\tABC Cafe POS System\n");
            System.out.println("Employee In Charge : " + empInCharge.getName() + "\nDate : " + new Date());
            System.out.println("\n           Main Menu");
            System.out.println("------------------------------");
            System.out.println("1 - Order");
            System.out.println("2 - Menu");
            System.out.println("3 - Member"); //just for use of checking
            System.out.println("4 - Voucher");
            System.out.println("5 - Summary");
            System.out.println("\n0 - Exit");
            System.out.print("Enter Selection > ");
            choice = getInput(choice);
            switch (choice) {
                case 1:
                    orderMenu(menu,cart,voucher,tableNo,member,empInCharge);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    break;
                default:
                    valid = false;
                    System.err.println("Invalid Input!");
                    System.err.flush();
            }
        } while (!valid);

    }

    public static void orderMenu(Menu[] menu, ArrayList<OrderDetails> cart, Voucher[] voucher, Table[] tableNo, Member[] member, Employee empInCharge) {
        boolean doneOrder = false;
        boolean continueInput;
        char choice = 0;
        do {
            clearScreen();
            continueInput = true;
            choice = getInput(choice);
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
                    System.err.flush();
                    systemPause();
            }
        } while (!continueInput);
        if (doneOrder) {    //if an order had done, go out of loop
            continueInput = false;
        }
        if (doneOrder) {

            Order order = settingBeforePayment(tableNo, cart, member, empInCharge); //get complete order
            payment(voucher, order);
        }
    }

    public static int getInput(int input) { //exception handling for int input
        try {
            input = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            scan.nextLine();
            return -1; //invalid
        }
        return input;
    }

    public static char getInput(char input) {  //exception handling for char input
        try {
            input = scan.next(".").charAt(0);
        } catch (Exception e) { //invalid
            scan.nextLine();
            return 0; //return null
        }
        return input;

    }

    public static void systemPause() {
        System.out.println("Press Enter To Continue...");
        System.out.flush();
        scan.nextLine();
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
                System.err.println("Invalid Input!");
                System.err.flush();
            }

        } while (!validItem);

        do { //ask user select variation ([L/R][I/H])
            size = getInput(size);
            size = Character.toUpperCase(size); // change to upper case

            if (isFood) {   //validation for size input
                if (Character.toString(size).matches("[.LR.]")) {
                    validItem = true;
                } else {
                    validItem = false;
                    System.err.println("Invalid Input.");
                    System.err.flush();
                    System.out.print("Please re-enter "
                            + "\n[L]arge/[R]egular > ");
                    System.out.flush();
                }
            } else {
                if (Character.toString(size).matches("[.IH.]")) {
                    validItem = true;
                } else {
                    validItem = false;
                    System.err.println("Invalid Input.");
                    System.err.flush();
                    System.out.print("Please re-enter "
                            + "\n[I]ced/[H]ot > ");
                    System.out.flush();
                }
            }

        } while (!validItem);
        itemID = itemID.concat(Character.toString(size)); //get complete id

        boolean same = false;
        for (Menu i : menu) {   //get the selected manu object
            if (i.itemID.equals(itemID)) {
                orderItem = i;
                do {
                    System.out.print("Enter Quantity    > ");
                    qtyOrder = getInput(qtyOrder);
                    if (qtyOrder <= 0) {
                        System.err.println("Invalid Quantity!");
                        System.err.flush();
                    }
                } while (qtyOrder <= 0);
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

        System.out.print("You Had Selected >> ");
        if (orderItem instanceof Food) {
            System.out.printf("%s - %s (%c) x %d (RM %.2f)\n", orderItem.itemID, orderItem.itemName, ((Food) orderItem).size, qtyOrder, orderItem.price * qtyOrder);
        } else {
            System.out.printf("%s - %s (%s) x %d (RM %.2f)\n", orderItem.itemID, orderItem.itemName, ((Beverage) orderItem).type, qtyOrder, orderItem.price * qtyOrder);
        }

        char choice = 0; //initailize with null
        boolean valid;
        do {
            valid = true;
            System.out.print("Are You Sure [Y/N] ? > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    if (same) {
                        cart.get(sameItemIndex).setQuantity(cart.get(sameItemIndex).getQuantity() + qtyOrder);
                    } else {
                        cart.add(orderDetails);
                    }
                    break;
                case 'N':
                    break;
                default:
                    valid = false;
                    System.err.println("Invalid Input!");
                    System.err.flush();
            }
        } while (!valid);
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
            System.err.flush();
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

        char cont = 0; //initialize null
        boolean valid;
        do {
            valid = true;
            System.out.print("Make Payment? [Y/N] > ");
            cont = getInput(cont);
            switch (Character.toUpperCase(cont)) {
                case 'Y':
                    return true;
                case 'N':
                    break;
                default:
                    valid = false;
                    System.err.println("Invalid Input");
            }
        } while (!valid);
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

    public static Order settingBeforePayment(Table[] tableNo, ArrayList<OrderDetails> cart, Member[] member, Employee emp) {
        OrderType orderType = new OrderType();
        Order order = new Order();

        //initialize null variable
        char type = 0;
        int table = 0;
        boolean valid;

        do {
            valid = true;
            System.out.print("[D]ine In/[T]ake away   > ");   //*need a looping or do it in another function
            type = getInput(type);
            switch (Character.toUpperCase(type)) {
                case 'D':
                    System.out.println("  Table");
                    for (int i = 0; i < tableNo.length; i += 2) {
                        System.out.printf("[%d] | [%d]\n", i + 1, i + 2);
                    }
                    do {
                        valid = true;
                        System.out.print("Select A Table  > "); //validation needed
                        table = getInput(table);
                        if (table <= 0 || table > tableNo.length) {
                            valid = false;
                            System.err.println("No Such Table!");
                        }
                    } while (!valid);
                    orderType = tableNo[table - 1]; //store selected table no
                    break;
                case 'T':
                    orderType = new Takeaway();
                    break;
                default:
                    valid = false;
                    System.err.println("Invalid Input!");
            }
        } while (!valid);

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

    //emp prob
    public static Payment payment(final Voucher[] voucher, Order order, ArrayList<OrderDetails> cart) {
        char choice = 'c';
        boolean invalid, haveVoucher, vValidDate, vMinSpend;
        boolean toEpay = false;
        boolean toCashpay = false;
        double passSubtotal = 0;
        char epayMethod = 'x';
        Payment pay;

        Voucher applyVoucher = new Voucher();
        do {
            invalid = false;
            vValidDate = false;
            haveVoucher = false;
            vMinSpend = false;
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
                    System.err.flush();
            }
        } while (invalid);

        while (haveVoucher) {
            scan.nextLine();    //buffer
            do {
                System.out.print("Enter Voucher Code > ");
                String voucherCode = scan.nextLine();
                for (Voucher i : voucher) {
                    if (i.getVoucherCode().equals(voucherCode.toUpperCase())) { //check voucher exist or nt       
                        applyVoucher = i;                                   //get the voucher code
                        haveVoucher = true;
                        vValidDate = i.isValid(voucherCode);                //check voucher date valid or nt
                        vMinSpend = i.checkMinSpend(order.calculateSubtotal(order.getOrderDetails()));                      //check for minSpend
                        break;
                    } else {
                        haveVoucher = false;
                    }
                }
                if (!haveVoucher) {
                    System.err.println("No Such Voucher Code!");
                } else if (haveVoucher && !vValidDate) {
                    System.err.println("Voucher Code Expired!");
                } else if (!vMinSpend) {
                    System.err.println("Did Not Meet Minimum Purchase");
                }
                System.err.flush();
                System.out.print("[T]ry Again/[C]ontinue Without Voucher? > ");
                choice = getInput(choice);
            } while (choice == 'T' || choice == 't');
        }

        do {

            System.out.println("   Subtotal(RM) : " + String.format("%.2f", Order.calculateSubtotal(cart)));          //display amount for payment
            if (haveVoucher) {
                System.out.println("   Discount(RM) : " + String.format("%.2f", applyVoucher.calculateDiscount(subtotal)));
                System.out.println("Grand Total(RM) : " + String.format("%.2f", pay.calculateGrandTotal()));
            } else {
                System.out.println("Grand Total(RM) : " + String.format("%.2f", pay.calculateGrandTotal()));
            }
            System.out.println("Please select payment method");//select payment option
            System.out.println("[E]-wallet/[C]ash");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'E':
                    toEpay = true;
                    passSubtotal = subtotal;
                    break;
                case 'C':
                    toCashpay = true;
                    passSubtotal = subtotal;
                    break;
                default:
                    System.err.println("Invalid Input!");
                    System.err.flush();
            }

        } while (choice != 'E' || choice != 'C');

        if (toEpay) {
            do {
                System.out.println("Choose platform");
                System.out.println("1. GrabPay");
                System.out.println("2. Touch'N'Go");
                epayMethod = getInput(epayMethod);
                switch (epayMethod) {
                    case 1:
                        if (haveVoucher) {
                            pay = new Ewallet("GRABPAY", "A12345", "REFERENCE", passSubtotal, applyVoucher.getDiscountRate());
                        } else {
                            pay = new Ewallet("GRABPAY", "A12345", "REFERENCE", passSubtotal);
                        }
                        break;
                    case 2:
                        if (haveVoucher) {
                            pay = new Ewallet("TOUCHNGO", "A12345", "REFERENCE", passSubtotal, applyVoucher.getDiscountRate());
                        } else {
                            pay = new Ewallet("TOUCHNGO", "A12345", "REFERENCE", passSubtotal);
                        }
                        break;
                    default:
                        System.err.println("Invalid Input!");
                        System.err.flush();
                }

                System.out.println("Confirm payment > ");
                choice = getInput(choice);
            } while (choice != 'Y');
        }

        if (toCashpay) {
            boolean finishPay = false;
            pay = new Cash();
            do {
                System.out.println("Enter Cash Received > ");
                double cashReceived = scan.nextDouble();
                finishPay = ((Cash) pay).checkAmount();
                if (haveVoucher) {
                    pay = new Cash(cashReceived, passSubtotal, applyVoucher.getDiscountRate());
                } else {
                    pay = new Cash(cashReceived, passSubtotal);
                }
            } while (!finishPay);  //here need to validate the amount must be more than grandtotal

            System.out.println("    Changes(RM) : " + String.format("%.2f", ((Cash) pay).getChange()));
        }
        return pay;
        //save the payment details into the object

//                QRcode.displayQRcode();
    }
}
