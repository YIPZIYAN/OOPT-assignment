package assignment;

import java.time.LocalDate;
import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assignment {

    static Scanner scan = new Scanner(System.in);
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        Bank bank = new Bank(1000); //set a basic money for transaction

        //Employee Details - Vallerie
        Employee[] empDetails = {new Employee("ZANICE", 'F', "0123456789", "Admin1111", "Manager", 10000.00),
            new Employee("RYAN", 'M', "0178888888", "Admin2222", "Clerk", 3000.00),
            new Employee("XAVIER", 'M', "0138796454", "Staff3333", "Clerk", 3000.00),
            new Employee("WINSON", 'M', "0189764533", "Staff4444", "Clerk", 3000.00),
            new Employee("VANESSA", 'F', "0135437755", "Staff5555", "Clerk", 3000.00)};

        Table[] tableNo = {new Table(1), new Table(2), new Table(3), new Table(4), new Table(5),
            new Table(6), new Table(7), new Table(8), new Table(9), new Table(10)};

        //Menu data - FC
        Menu[] menu = {new Food("Fried Noodle", "F001L", 10.00, 'L'), //arg (name, ID, price, size)
            new Food("Fried Noodle", "F001R", 8.00, 'R'), // R = Regular Size
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

        Member[] member = {new Member("YZY", "012-1231123"),
            new Member("Florryn", "011-23222233"),
            new Member("Vallerie", "012-7482947"),
            new Member("Jay Cao", "013-7283747"),
            new Member("Siva Navin", "012-6475837"),
            new Member("Chai Xun", "011-27589604"),
            new Member("August Kun", "014-2324856"),
            new Member("Justin", "012-3750122"),
            new Member("Ma Yun", "012-6401236"),
            new Member("Jimmy ", "013-0126801")
        };
        //name,rate,min,cap,exp
        Voucher[] voucher = {new Voucher("ABC123", 40, 10, 10, LocalDate.of(2023, 12, 14)),
            new Voucher("HELLO", 40, 30, 10, LocalDate.of(2022, 12, 14)),
            new Voucher("IEMIAT", 20, 20, 20, LocalDate.of(2022, 11, 11)),
            new Voucher("INIJ", 10, 15, 20, LocalDate.of(2022, 10, 10)),
            new Voucher("UNKUNK", 15, 25, 20, LocalDate.of(2022, 10, 1)),
            new Voucher("CTRLM", 5, 10, 10, LocalDate.of(2022, 9, 16)),
            new Voucher("QMZZR", 10, 10, 10, LocalDate.of(2022, 8, 31)),
            new Voucher("DJHWSCXK", 20, 20, 10, LocalDate.of(2022, 8, 18))};

        ArrayList<OrderDetails> cart = new ArrayList<>();
        ArrayList<Order> orderRecord = new ArrayList<>();

        //intialize for item summary sales
        ArrayList<OrderDetails> itemSales = new ArrayList<>();
        for (Menu i : menu) {
            itemSales.add(new OrderDetails(i, 0));
        }

        Employee empInCharge = new Employee();
        Login login = new Login(empDetails);

        boolean loginSuccessful = false;
        do {
            loginSuccessful = login.isLoginSucess();
            if (loginSuccessful) {
                for (Employee empDetail : empDetails) {
                    if (empDetail.getEmpID().equals(login.getId().toUpperCase())) {
                        empInCharge = empDetail;
                    }
                }
                login.frame.setVisible(!loginSuccessful);
            }

        } while (!loginSuccessful);

        int choice = 0;
        boolean doneOrder = false;
        do {
            clearScreen();
            System.out.println("\n\tABC Cafe POS System\n");
            System.out.println("Employee In Charge : " + empInCharge.getName() + "\nDate : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            System.out.println("\n           + Main Menu +");
            System.out.println("===================================");
            System.out.println("        1 - Display Menu");
            System.out.println("        2 - Take Order");
            System.out.println("        3 - Member"); // to display member points
            System.out.println("        4 - Voucher");
            if (empInCharge.getPosition().equals("Manager")) {
                System.out.println("        5 - Sales Summary");
                System.out.println("        6 - Account");
            }
            System.out.println("        0 - Exit");
            System.out.println("===================================");
            System.out.print("Enter Selection > ");
            choice = getInput(choice);
            switch (choice) {
                case 1:
                    displayMenu(menu);
                    systemPause();
                    break;
                case 2:
                    orderMenu(menu, cart, voucher, tableNo, member, empInCharge, orderRecord, itemSales);
                    break;
                case 3:
                    clearScreen();
                    displayMember(member);
                    systemPause();
                    break;
                case 4:
                    clearScreen();
                    displayVoucher(voucher);
                    systemPause();
                    break;
                case 0:
                    System.exit(0);
                    break;
                case 5:
                    if (empInCharge.getPosition().equals("Manager")) {
                        clearScreen();
                        salesSummary(itemSales, menu);
                        systemPause();
                        break;
                    }
                case 6: //only can access if emp is manager level
                    if (empInCharge.getPosition().equals("Manager")) {
                        displayBank(bank);
                        break;
                    }
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
                    systemPause();
            }
        } while (choice != 0);

    }

    public static void orderMenu(Menu[] menu, ArrayList<OrderDetails> cart, Voucher[] voucher, Table[] tableNo, Member[] member, Employee empInCharge, ArrayList<Order> orderRecord, ArrayList<OrderDetails> itemSales) {
        boolean doneOrder = false;
        boolean continueInput;
        int choice = 0;
        do {
            clearScreen();
            continueInput = true;
            System.out.println("\n           + Order +");
            System.out.println("==============================");
            System.out.println("       1 - Start Order");
            System.out.println("       2 - Cart");
            System.out.println("       3 - Payment");
            System.out.println("       0 - Go Back");
            System.out.println("==============================");
            System.out.print("Enter Selection > ");
            choice = getInput(choice);
            switch (choice) {
                case 1:
                    startOrder(menu, cart);
                    break;
                case 2:
                    if (!cart.isEmpty()) {
                        cartMenu(Order.getTotalOrder(), menu, cart);
                    } else {
                        System.out.println(RED + "Cart Is Empty!!" + RESET);
                        systemPause();
                    }
                    break;
                case 3:
                    if (!cart.isEmpty()) {
                        doneOrder = proceedPayment(Order.getTotalOrder(), cart);
                    } else {
                        System.out.println(RED + "Cart Is Empty!!" + RESET);
                        systemPause();
                    }
                    break;
                case 0:
                    continueInput = false;
                    break;
                default:
                    System.out.println(RED + "Invalid Selection!!" + RESET);
                    systemPause();
            }
            if (doneOrder) {    //if an order had done, go out of loop
                continueInput = false;
            }
        } while (continueInput);

        if (doneOrder) {
            Order order = settingBeforePayment(tableNo, cart, member, empInCharge); //get complete order
            for (OrderDetails i : itemSales) {
                for (OrderDetails orderDetail : order.getOrderDetails()) {
                    if (orderDetail.getOrderList().itemID.equals(i.getOrderList().itemID)) {
                        i.setQuantity(i.getQuantity() + orderDetail.getQuantity());
                    }
                }
            }
            Payment paymentDone = payment(voucher, order);
            if (order.getMemberDetails() != null) {
                order.getMemberDetails().addPoint((int) Math.round(order.calculateSubtotal(cart)));
            }
            receipt(paymentDone, order);
            orderRecord.add(order);
            cart.clear();
        }
    }

    public static void salesSummary(ArrayList<OrderDetails> itemSales, Menu[] menu) {
        double sumTotal = 0;
        int salesCount = 0;
        int i = 0;
        System.out.println("\n                         + Sales Summary +");
        System.out.println("                    Date : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("                                                          Sales");
        System.out.println("      Item List                                Qty     Amount (RM) ");
        System.out.println(" ===================================================================");
        for (OrderDetails orderDetail : itemSales) {

            try {
                if (itemSales.get(i).getOrderList().itemName.equals(itemSales.get(i - 1).getOrderList().itemName)) {
                    System.out.println(itemSales.get(i).displaySameSalesDetails());
                } else {
                    System.out.println(itemSales.get(i).displaySalesDetails());
                }
            } catch (Exception e) {
                System.out.println(itemSales.get(i).displaySalesDetails());
            }
            i++;
        }
        System.out.println(" ===================================================================");
    }

    public static void displayMember(final Member[] member) {
        clearScreen();
        System.out.println("\n             + Member List +");
        System.out.println("==========================================");
        System.out.println(" ID     Name         Contact No      Point");
        for (Member i : member) {
            System.out.println(" " + i);
        }
        System.out.println("==========================================");
    }

    public static void displayVoucher(final Voucher[] voucher) {
        clearScreen();
        System.out.println("\n                    + Voucher List +");
        System.out.println("======================================================");
        System.out.println(" Code      Rate(%)   Min Spend  Cap Value  Expire Date");
        for (Voucher i : voucher) {
            System.out.println(" " + i);
        }

        System.out.println("======================================================");
    }

    public static void displayBank(final Bank bank) {
        char choice = 0;
        do {
            clearScreen();
            System.out.println(bank);
            System.out.print("Make A Copy [Y/N] ? > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    bank.printOut();
                    systemPause();
                    break;
                case 'N':
                    break;
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }

        } while (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N');

    }

    public static void startOrder(final Menu[] menu, ArrayList<OrderDetails> cart) {
        boolean validItem = false;
        boolean isFood;
        boolean isBeverage;
        boolean cont;

        String itemID;
        char size = 'x';

        Menu orderItem = new Menu(); //get the ordered menu details
        int qtyOrder = 0;
        OrderDetails orderDetails = new OrderDetails(); //save to orderDetails and add to cart
        int sameItemIndex = 0;

        do {
            clearScreen();
            displayMenu(menu);
            cont = false;
            do {
                isFood = false;
                isBeverage = false;
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
                    System.out.println(RED + "Invalid Input!!" + RESET);
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
                        System.out.println(RED + "Invalid Input!!" + RESET);
                        System.out.print("Please re-enter "
                                + "\n[L]arge/[R]egular   > ");
                    }
                } else {
                    if (Character.toString(size).matches("[.IH.]")) {
                        validItem = true;
                    } else {
                        validItem = false;
                        System.out.println(RED + "Invalid Input!!" + RESET);
                        System.out.print("Please re-enter "
                                + "\n[I]ced/[H]ot       > ");
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
                            System.out.println(RED + "Invalid Quantity!!" + RESET);
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
                        System.out.println(RED + "Invalid Input!!" + RESET);
                }
            } while (!valid);

            do {
                valid = true;
                choice = 0;
                System.out.print("Anymore [Y/N] ? > ");
                choice = getInput(choice);
                switch (Character.toUpperCase(choice)) {
                    case 'Y':
                        cont = true;
                        break;
                    case 'N':
                        cont = false;
                        break;
                    default:
                        valid = false;
                        System.out.println(RED + "Invalid Input!!" + RESET);
                }
            } while (!valid);
        } while (cont);
    }

    public static void displayCart(int orderID, final ArrayList<OrderDetails> cart) {
        clearScreen();
        Collections.sort(cart, OrderDetails.Comparator);
        double subtotal = 0;
        int sameCount = 0;
        System.out.println("                         + Cart +\n");
        System.out.println("Order ID : " + String.format("%04d", ++orderID));
        System.out.println("==============================================================");
        System.out.printf("%-3s %-9s%-16s%-10s%-6s%-9s%s\n", "No", "Item ID", "Item Name", "Price", "Type", "Quantity", "Subtotal");
        //here
        for (int i = 0; i < cart.size(); i++) {
            try {
                if (cart.get(i).getOrderList().itemName.equals(cart.get(i - 1).getOrderList().itemName)) {
                    System.out.printf("%2d. %s\n", i + 1, cart.get(i).displaySameOrderDetails());
                } else {
                    System.out.printf("%2d. %s\n", i + 1, cart.get(i));
                }
            } catch (Exception e) {
                System.out.printf("%2d. %s\n", i + 1, cart.get(i));
            } finally {
                subtotal += cart.get(i).calculateSubtotal(); //add total
            }
        }
        System.out.println("==============================================================");
        System.out.printf("Total = RM %.2f\n", subtotal);
    }

    public static boolean proceedPayment(int orderID, final ArrayList<OrderDetails> cart) {
        displayCart(orderID, cart);
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
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }
        } while (!valid);

        return false;
    }

    public static void cartMenu(int orderID, final Menu[] menu, ArrayList<OrderDetails> cart) {
        int choice = 0;
        do {
            clearScreen();
            displayCart(orderID, cart);
            System.out.println("\n           + Action +");
            System.out.println("==============================");
            System.out.println("       1 - Change Type");
            System.out.println("       2 - Delete An Item");
            System.out.println("       3 - Clear Cart");
            System.out.println("       0 - Go Back");
            System.out.println("==============================");
            System.out.print("Enter Selection > ");
            choice = getInput(choice);
            switch (choice) {
                case 1:
                    editCart(menu, cart);
                    break;
                case 2:
                    deleteCart(cart);
                    break;
                case 3:
                    clearCart(cart);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
                    systemPause();
            }

            if (cart.isEmpty()) {
                break;
            }

        } while (choice != 0);

    }

    public static void editCart(final Menu[] menu, ArrayList<OrderDetails> cart) {
        boolean valid;
        int cartNo = 0;
        int sameItemIndex = 0;
        char choice = 0;
        String changeType = "Hot";
        char changeSize = 'L';
        String changeID = "";

        System.out.print("Select A Cart No. > ");
        cartNo = getInput(cartNo);
        if (cartNo <= 0 || cartNo > cart.size()) {
            System.out.println(RED + "Invalid Input!!" + RESET);
            systemPause();
            return;
        }

        System.out.print("Change :");
        if (cart.get(cartNo - 1).getOrderList() instanceof Food) {
            if (((Food) cart.get(cartNo - 1).getOrderList()).size == changeSize) {
                changeSize = 'R';
            }
            changeID = cart.get(cartNo - 1).getOrderList().itemID.substring(0, cart.get(cartNo - 1).getOrderList().itemID.length() - 1) + changeSize;
            System.out.printf("%s - %s (%c) -> (%c)\n", cart.get(cartNo - 1).getOrderList().itemID, cart.get(cartNo - 1).getOrderList().itemName, ((Food) cart.get(cartNo - 1).getOrderList()).size, changeSize);
        } else {
            if (((Beverage) cart.get(cartNo - 1).getOrderList()).type.equals(changeType)) {
                changeType = "Iced";
            }
            changeID = cart.get(cartNo - 1).getOrderList().itemID.substring(0, cart.get(cartNo - 1).getOrderList().itemID.length() - 1) + changeType.charAt(0);
            System.out.printf("%s - %s (%s) -> (%s)\n", cart.get(cartNo - 1).getOrderList().itemID, cart.get(cartNo - 1).getOrderList().itemName, ((Beverage) cart.get(cartNo - 1).getOrderList()).type, changeType);
        }

        Menu changeMenu = new Menu();
        for (Menu i : menu) {
            if (i.itemID.equals(changeID)) {
                changeMenu = i;
            }
        }
        OrderDetails changeCart = new OrderDetails(changeMenu, cart.get(cartNo - 1).getQuantity());

        do {
            valid = true;
            System.out.print("Are You Sure [Y/N] > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    boolean same = false;

                    for (int j = 0; j < cart.size(); j++) {   //check same item in cart, if same just add qty
                        if (cart.get(j).getOrderList().equals(changeCart.getOrderList())) {
                            same = true;
                            sameItemIndex = j;
                            break;
                        }
                    }
                    if (same) {
                        cart.get(sameItemIndex).setQuantity(cart.get(sameItemIndex).getQuantity() + cart.get(cartNo - 1).getQuantity());
                        cart.remove(cartNo - 1);
                    } else {
                        cart.set(cartNo - 1, changeCart);
                    }

                    break;
                case 'N':
                    break;
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
                    valid = false;
            }
        } while (!valid);
    }

    public static void deleteCart(ArrayList<OrderDetails> cart) {
        boolean valid;
        int cartNo = 0;
        char choice = 0;

        System.out.print("Select A Cart No. > ");
        cartNo = getInput(cartNo);
        if (cartNo <= 0 || cartNo > cart.size()) {
            System.out.println(RED + "Invalid Input!!" + RESET);
            systemPause();
            return;
        }

        System.out.print("Delete :");
        if (cart.get(cartNo - 1).getOrderList() instanceof Food) {
            System.out.printf("%s - %s (%c) x %d (RM %.2f)\n", cart.get(cartNo - 1).getOrderList().itemID, cart.get(cartNo - 1).getOrderList().itemName, ((Food) cart.get(cartNo - 1).getOrderList()).size, cart.get(cartNo - 1).getQuantity(), cart.get(cartNo - 1).getQuantity() * cart.get(cartNo - 1).getOrderList().price);
        } else {
            System.out.printf("%s - %s (%s) x %d (RM %.2f)\n", cart.get(cartNo - 1).getOrderList().itemID, cart.get(cartNo - 1).getOrderList().itemName, ((Beverage) cart.get(cartNo - 1).getOrderList()).type, cart.get(cartNo - 1).getQuantity(), cart.get(cartNo - 1).getQuantity() * cart.get(cartNo - 1).getOrderList().price);
        }

        do {
            valid = true;
            System.out.println("Are You Sure [Y/N] > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    cart.remove(cartNo - 1);
                    break;
                case 'N':
                    break;
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
                    valid = false;
            }
        } while (!valid);

    }

    public static void clearCart(ArrayList<OrderDetails> cart) {
        boolean valid;
        char choice = 0;
        do {
            valid = true;
            System.out.print("Are You Sure To Clear" + RED + " ALL " + RESET + "Item [Y/N] ? > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    cart.clear();
                    break;
                case 'N':
                    break;
                default:
                    System.out.println(RED + "Invalid Input!!" + RESET);
                    valid = false;
            }
        } while (!valid);
    }

    public static void displayMenu(final Menu[] menu) {
        //display menu - FC
        clearScreen();
        String check1, check2;
        System.out.println("\n                   + MENU + ");
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
                    System.out.println("\n      Table");
                    System.out.printf("------------------\n");
                    for (int i = 0; i < tableNo.length; i += 2) {
                        System.out.printf("    [%d] | [%d]\n", i + 1, i + 2);
                    }
                    System.out.printf("------------------\n");
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
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }
        } while (!valid);

        boolean isMember = false;
        char choice = 0;
        do {
            valid = true;
            System.out.print("Is A Member? [Y/N] > ");
            choice = getInput(choice);
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    isMember = true;
                    break;
                case 'N':
                    order = new Order(orderType, emp, cart);
                    break;
                default:
                    valid = false;
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }
        } while (!valid);

        while (isMember) {
            System.out.print("Enter Member ID > ");
            String memberID = scan.nextLine().toUpperCase();
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
                System.out.println(RED + "No Such Member ID!" + RESET);
                do {
                    valid = true;
                    System.out.print("[T]ry Again/[C]ontinue Without Member? > ");
                    choice = getInput(choice);
                    switch (Character.toUpperCase(choice)) {
                        case 'T':
                            isMember = true;
                            break;
                        case 'C':
                            order = new Order(orderType, emp, cart); // create object with no member
                            break;
                        default:
                            System.out.println(RED + "Invalid Input!!" + RESET);
                            valid = false;
                    }
                } while (!valid);
            }
        }

        return order;
    }

    public static Payment payment(final Voucher[] voucher, final Order order) {
        char choice = 'c';
        boolean valid, haveVoucher, vValidDate, vMinSpend;
        boolean toEpay;
        boolean toCashpay;
        boolean toCardpay;
        double subtotal = order.calculateSubtotal(order.getOrderDetails());
        double grandTotal = 0;
        Cash checkCash = new Cash();

        Voucher applyVoucher = new Voucher();
        char voucherChoice = 0;
        do {
            valid = true;
            vValidDate = false;
            haveVoucher = false;
            vMinSpend = false;
            System.out.print("Any Voucher? [Y/N] > ");
            voucherChoice = getInput(voucherChoice);
            switch (Character.toUpperCase(voucherChoice)) {
                case 'Y':
                    haveVoucher = true;
                    break;
                case 'N':
                    break;
                default:
                    valid = false;
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }
        } while (!valid);

        while (haveVoucher) {
            System.out.print("Enter Voucher Code > ");
            String voucherCode = scan.nextLine();
            for (Voucher i : voucher) {
                if (i.getVoucherCode().equals(voucherCode.toUpperCase())) { //check voucher exist or nt       
                    applyVoucher = i;                                   //get the voucher code
                    haveVoucher = true;
                    vValidDate = i.isValid(voucherCode.toUpperCase());                //check voucher date valid or nt
                    vMinSpend = i.checkMinSpend(subtotal);                      //check for minSpend
                    break;
                } else {
                    haveVoucher = false;
                }
            }

            if (!haveVoucher) {
                System.out.println(RED + "No Such Voucher Code!" + RESET);
            } else if (!vValidDate) {
                System.out.println(RED + "Voucher Code Expired!" + RESET);
            } else if (!vMinSpend) {
                System.out.println(RED + "Did Not Meet Minimum Purchase" + RESET);
            } else {
                break; //valid voucher
            }

            do {
                valid = true;
                System.out.print("[T]ry Again/[C]ontinue Without Voucher? > ");
                choice = getInput(choice);
                switch (Character.toUpperCase(choice)) {
                    case 'T':
                        haveVoucher = true;//loop again
                        break;
                    case 'C':
                        haveVoucher = false;
                        break;
                    default:
                        valid = false;
                        System.out.println(RED + "Invalid Input!!" + RESET);
                }
            } while (!valid);

        }

        System.out.println(".............................");
        System.out.println("..         Payment         ..");
        System.out.println(".............................");
        System.out.println("Subtotal(RM)       : " + String.format("%8.2f", subtotal));          //display amount for payment
        System.out.println("Tax 6% (RM)        : " + String.format("%8.2f", (Order.TAX - 1) * subtotal));
        if (order.getOrderType() instanceof Takeaway) {
            System.out.println("Packaging Fees(RM) : " + String.format("%8.2f", Takeaway.charges));
        }
        if (haveVoucher) {
            grandTotal = order.calculateGrandTotal(subtotal, applyVoucher.calculateDiscount(subtotal));
            System.out.println("Discount(RM)       : " + String.format("%8.2f", applyVoucher.calculateDiscount(subtotal)));
        } else {
            grandTotal = order.calculateGrandTotal(subtotal);
        }
        System.out.println("Grand Total(RM)    : " + String.format("%8.2f", grandTotal));

        int payMethod = 0;
        String ewalletName = "";    //ewallet details
        int epayMethod = 0;
        double cashReceived = 0;
        do { //**to do:cancel order
            valid = true;
            toCashpay = false;
            toEpay = false;
            toCardpay = false;
            System.out.println("\nPlease select payment method");//select payment option
            System.out.println("1. Ewallet");
            System.out.println("2. Cash");
            System.out.print("Enter Selection > ");
            payMethod = getInput(payMethod);
            switch (payMethod) {
                case 1:
                    toEpay = true;
                    QRcode qr;
                    qr = QRcode.displayQRcode();    //show qrcode
                    OUTER:
                    do {
                        valid = true;
                        System.out.println("\nChoose Platform");
                        System.out.println("1. GrabPay");
                        System.out.println("2. Touch'N'Go");
                        System.out.println("0. Change Method");
                        System.out.print("Enter Selection > ");
                        epayMethod = getInput(epayMethod);
                        switch (epayMethod) {
                            case 1:
                                ewalletName = "GrabPay";
                                break;
                            case 2:
                                ewalletName = "TNG";
                                break;
                            case 0:
                                valid = false;
                                break OUTER;
                            default:
                                valid = false;
                                System.out.println(RED + "Invalid Input!!" + RESET);
                        }
                    } while (!valid);
                    QRcode.closeQRcode(qr); //stop display
                    break;
                case 2:
                    toCashpay = true;
                    System.out.print("Enter Cash Received > RM ");
                    cashReceived = getInput(cashReceived);
                    scan.nextLine();
                    if (cashReceived == 0) { //invalid
                        valid = false;
                        System.out.println(RED + "Invalid Input!!" + RESET);
                    } else {
                        checkCash.setCashReceive(cashReceived);
                        if (!checkCash.checkAmount(grandTotal)) {
                            valid = false;
                            System.out.println(RED + "Insufficient Amount!" + RESET);

                        }
                        checkCash.setChange(cashReceived - grandTotal);
                    }
                    break;
                default:
                    valid = false;
                    System.out.println(RED + "Invalid Input!!" + RESET);
            }

            if (valid) {
                do {
                    System.out.print("Confirm payment [Y/N] > ");
                    choice = getInput(choice);
                    switch (Character.toUpperCase(choice)) {
                        case 'Y':
                            break;
                        case 'N':
                            valid = false;//continue looping
                            break;
                        default:
                            System.out.println(RED + "Invalid Input!!" + RESET);
                    }
                } while (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N');
            }

        } while (!valid);

        Payment pay = null; //store payment

        if (toEpay) {
            if (haveVoucher) {
                pay = new Ewallet(ewalletName, "A12345", "REFERENCE", grandTotal, applyVoucher.calculateDiscount(subtotal));
            } else {
                pay = new Ewallet(ewalletName, "A12345", "REFERENCE", grandTotal);
            }
            pay.transaction(grandTotal); //transaction with bank
        } else {
            System.out.println("Changes(RM) : " + String.format("%.2f", checkCash.getChange()));
            if (haveVoucher) {
                pay = new Cash(cashReceived, grandTotal, applyVoucher.calculateDiscount(subtotal));
            } else {
                pay = new Cash(cashReceived, grandTotal);
            }
            pay.transaction(cashReceived); //transaction with bank
        }
        System.out.println("\nTo print receipt... ");
        systemPause();
        return pay;
    }

    public static void receipt(final Payment payment, final Order order) {
        clearScreen();
        System.out.println("");
        System.out.println("                          ABC Cafe");
        System.out.println("                Lot 123, Jalan Genting Kelang,");
        System.out.println("                 Setapak, 53300 Kuala Lumpur");
        System.out.println("                   Tel No: 603 - 41625833");
        System.out.println("");
        System.out.println("                         + RECEIPT +");
        System.out.println("");
        System.out.println("Receipt No : " + order.getOrderID());
        System.out.println("Staff Name : " + order.getEmpDetails().getName());
        System.out.println("Date       : " + order.getOrderDate());
        System.out.println("");
        System.out.println("==========================================================");
        System.out.printf("%-9s%-16s%-10s%-6s%-9s%s\n", "Item ID", "Item Name", "Price", "Type", "Quantity", "Subtotal");
        System.out.println("==========================================================");
        for (int i = 0; i < order.getOrderDetails().size(); i++) {
            try {
                if (order.getOrderDetails().get(i).getOrderList().itemName.equals(order.getOrderDetails().get(i - 1).getOrderList().itemName)) {
                    System.out.println(order.getOrderDetails().get(i).displaySameOrderDetails());
                } else {
                    System.out.println(order.getOrderDetails().get(i));
                }
            } catch (Exception e) {
                System.out.println(order.getOrderDetails().get(i));
            }
        }
        System.out.println("==========================================================");
        System.out.printf("   %-20s %33.2f\n", "Subtotal (RM)", order.calculateSubtotal(order.getOrderDetails()));
        System.out.printf("   %-20s %33.2f\n", "Tax 6%", ((Order.TAX - 1) * order.calculateSubtotal(order.getOrderDetails())));
        if (order.getOrderType() instanceof Takeaway) {
            System.out.printf("   %-20s %33.2f\n", "Packaging Fee (RM)", 3.00);
        }

        System.out.printf("   %-20s %33.2f\n", "Voucher ", payment.discountAmount); //change to discountAmount 
        System.out.printf("   %-20s %33.2f\n", "Total ", payment.grandTotal);
        if (payment instanceof Cash) {
            System.out.printf("\n   %-20s %33.2f\n", "Cash", ((Cash) payment).getCashReceive());
            System.out.printf("   %-20s %33.2f\n", "Change", ((Cash) payment).getChange());
        }
        System.out.println("==========================================================");
        System.out.println("                 Thank You and See You Again!!");
        System.out.println("");
        System.out.println("");
        systemPause();

    }

    //general function
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
        String buf;
        try {
            buf = scan.nextLine();
            if (buf.length() > 1) {
                throw new Exception();
            }
            input = buf.charAt(0);
        } catch (Exception e) { //invalid
            return 0; //return null
        }

        return input;
    }

    public static double getInput(double input) {  //exception handling for double input
        try {
            input = scan.nextDouble();
        } catch (Exception e) { //invalid
            scan.nextLine();
            return 0; //return null
        }
        return input;
    }

    public static void systemPause() {
        System.out.println("Press Enter To Continue...");
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
}
