/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import static java.lang.System.console;
import java.util.Scanner;

/**
 *
 * @author End User
 */
public class Admin {
    
    public static void Admin(){
        Employee[] empDetails = new Employee[100];
        empDetails[0] = new Employee("Zanice Tay", 'F', "0123456789", "Admin1111", "Manager", 10000.00);
        empDetails[1] = new Employee("Yves Wong", 'M', "0178888888", "Admin2222", "Clerk", 3000.00);
        empDetails[2] = new Employee("Xavier Teo", 'M', "0138796454", "Staff3333", "Clerk", 3000.00);
        empDetails[3] = new Employee("Winson Fang", 'M', "018976453", "Staff4444", "Clerk", 3000.00);
        empDetails[4] = new Employee("Vanessa Cheah", 'F', "01", "Staff5555", "Clerk", 3000.00);
        int choice = 0;
        Scanner input = new Scanner(System.in);
        do{
            
            System.out.println("\nEmployee Menu");
            System.out.println("1. Add new Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. View Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Delete Existing Employee");
            System.out.println("6. Return to Main Menu");
            
            choice = input.nextInt();
            
            switch(choice){
                case 1: add(empDetails);
                        break;
                case 2: update(empDetails);
                        break;
                case 3: view(empDetails);
                        break;
                case 4: search(empDetails);
                        break;
                case 5: delete(empDetails);
                        break;
                case 6: break; 
                default:System.out.println("Invalid choice");
                        break;
                        
            }
            
        }while(choice != 6);   
        
    }
    
    public static void add(Employee[] empDetails){
        String idT;
        String nameT;
        char genderT;
        String contactNoT;
        String passwordT;
        String positionT;
        double salaryT;
        char confirmation;
        char cont;
        
        
        do{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Employee ID: ");
            idT = input.nextLine();
        
            System.out.println("Enter Employee name: ");
            nameT = input.nextLine();
        
            System.out.println("Enter Gender: ");
            genderT = input.next().charAt(0);
        
            System.out.println("Enter Contact Number: ");
            contactNoT =  input.nextLine();
        
            System.out.println("Enter Position: ");
            positionT = input.nextLine();
        
            System.out.println("Enter Salary: ");
            salaryT = input.nextDouble();
        
            System.out.println("Employee ID     : " + idT);
            System.out.println("Employee Name   : " + nameT);
            System.out.println("Gender          : " + genderT);
            System.out.println("Contact No      : " + contactNoT);
            System.out.println("Position        : " + positionT);
            System.out.println("Salary          : " + salaryT);
            System.out.println("Please confirm upon your adding (Y/N): ");
            confirmation = input.next().charAt(0);
        
            if(Character.toUpperCase(confirmation) == 'Y'){
                System.out.println("Enter Employee Password");
                passwordT = input.nextLine();
                empDetails[Employee.getEmpCount()] = new Employee(nameT, genderT, contactNoT, passwordT, positionT, salaryT);
            
        }
        
            System.out.println("Do you want to add another staff? (Y/N): ");
            cont = input.next().charAt(0);
        }while(Character.toUpperCase(cont) != 'Y');
        
        
    }
    
    public static void update(Employee[] empDetails){
        
        
    }
    
    public static void view(Employee[] empDetails){
        Scanner input = new Scanner(System.in);
        System.out.println("================================Staff Information===============================");
        System.out.printf("|  %-6s|        %-12s|    %-5s|  %-11s| %-9s|    %10s|", "ID","Name", "Gender","Contact No.", "Postion", "Salary");
        for(int i = 0; i < empDetails.length; i++){
            System.out.println(empDetails[i]);
        }
        System.out.println("================================================================================");
        System.out.println("Press any key to continue");
        input.nextLine();
    }
    
    public static void search(Employee[] empDetails){
        
    }
    
    public static void delete(Employee[] empDetails){
        
    }
    
}
