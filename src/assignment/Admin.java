package assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vallerie
 */
public class Admin extends Employee {

    public static void Admin(Employee[] empDetails) {

        int choice = -1;                                                                                     //Choice from employee menu
        Scanner input = new Scanner(System.in);
        do {     //check valid option
            do {     //check input is an integer
                System.out.println("\n\n\n\t\t=================");
                System.out.println("\t\t| Employee Menu |");
                System.out.println("\t\t=================");
                System.out.println("1. View Employee");
                System.out.println("2. Search Employee");
                System.out.println("0. Return to Main Menu");
                System.out.print("Enter your choice: ");

                try {
                    choice = input.nextInt();

                    switch (choice) {
                        case 1:
                            view(empDetails);
                            break;
                        case 2:
                            search(empDetails);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("\nInvalid choice");
                            System.out.print("\nPress any key to continue");
                            input.nextLine();
                            input.nextLine();
                            break;

                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid choice");
                    System.out.print("\nPress any key to continue");
                    input.nextLine();
                    input.nextLine();
                }

            } while (choice == -1);
        } while (choice != 0);

    }

    public static void view(Employee[] empDetails) {
        Scanner input = new Scanner(System.in);
        System.out.printf("\n\n------------------------------- Staff Information -----------------------------\n");
        System.out.printf("|  %-6s|        %-12s|  %-7s|  %-11s| %-9s|   %-9s|\n", "ID", "Name", "Gender", "Contact No.", "Postion", "Salary");
        System.out.println("-------------------------------------------------------------------------------");
        for (int i = 0; i < empDetails.length; i++) {
            if (empDetails[i] != null) {
                System.out.print(empDetails[i]);
                System.out.println("-------------------------------------------------------------------------------");
            }

        }

        System.out.print("\nPress any key to continue");
        input.nextLine();
    }

    public static void search(Employee[] empDetails) {
        String searchEmpId;
        String searchEmpName;
        int option = -1;
        int error;
        int notFound;
        Scanner input = new Scanner(System.in);

        do {
            do {
                error = 0;
                notFound = 0;
                System.out.printf("\n%50s\n", "=====================");
                System.out.printf("%50s\n", "| Search Staff Data |");
                System.out.printf("%50s\n", "=====================");
                System.out.println("Search Staff Data By -->");
                System.out.println("1. Employee ID");
                System.out.println("2. Employee Name");
                System.out.println("0. Return to Menu");
                System.out.printf("Enter your choice: ");
                try {
                    option = input.nextInt();

                    switch (option) {
                        case 1:

                            System.out.print("Enter Employee ID to Search: ");
                            searchEmpId = input.nextLine();
                            searchEmpId = input.nextLine();

                            for (int i = 0; i < empDetails.length; i++) {
                                if (empDetails[i] != null) {
                                    if (searchEmpId.equalsIgnoreCase(empDetails[i].getEmpID())) {
                                        System.out.print("\n\nSearch Result\n");
                                        System.out.println("--------------");
                                        System.out.println("Employee ID     : " + empDetails[i].getEmpID());
                                        System.out.println("Employee Name   : " + empDetails[i].getName());
                                        System.out.println("Gender          : " + empDetails[i].getGender());
                                        System.out.println("Contact No      : " + empDetails[i].getContactNo());
                                        System.out.println("Position        : " + empDetails[i].getPosition());
                                        System.out.println("Salary          : RM" + empDetails[i].getSalary());

                                        System.out.print("\nPress any key to continue");
                                        input.nextLine();
                                    } else {
                                        notFound++;
                                    }
                                }

                            }

                            if (notFound == empDetails.length) {
                                System.out.println("\nEmployee ID not found");
                                input.nextLine();
                                input.nextLine();
                                error = 1;
                            }

                            break;

                        case 2:
                            System.out.print("Enter Employee Name to Search: ");
                            searchEmpName = input.nextLine();
                            searchEmpName = input.nextLine();
                            for (int i = 0; i < empDetails.length; i++) {
                                if ((empDetails[i].getName()).indexOf(searchEmpName.toUpperCase()) >= 0) { // check name ignoring the case
                                    System.out.print("\n\nSearch Result\n");
                                    System.out.println("--------------");
                                    System.out.println("Employee ID     : " + empDetails[i].getEmpID());
                                    System.out.println("Employee Name   : " + empDetails[i].getName());
                                    System.out.println("Gender          : " + empDetails[i].getGender());
                                    System.out.println("Contact No      : " + empDetails[i].getContactNo());
                                    System.out.println("Position        : " + empDetails[i].getPosition());
                                    System.out.println("Salary          : " + empDetails[i].getSalary());

                                    System.out.print("\nPress any key to continue");
                                    input.nextLine();

                                } else {
                                    notFound++;
                                }

                                if (notFound == empDetails.length) {
                                    System.out.println("\nEmployee Name not found\n");
                                    System.out.print("Press any key to continue");
                                    new java.util.Scanner(System.in).nextLine();
                                    error = 1;
                                }
                            }
                            break;
                        case 0:
                            break;

                        default:
                            System.out.println("\nPlease enter a valid option");
                            System.out.println("\nPress any key to continue");
                            input.nextLine();
                            input.nextLine();
                            error = 1;
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid input");
                    System.out.print("\nPress any key to continue");
                    input.nextLine();
                    input.nextLine();
                }
            } while (option == -1);
        } while (error == 1);

    }

}
