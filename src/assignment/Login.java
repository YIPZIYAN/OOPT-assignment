package assignment;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @Vallerie
 */
public class Login {

    HashMap<String, String> idPassword = new HashMap<>();
    Scanner input = new Scanner(System.in);
    private String id;
    private String password;

    public Login() {
        
    }

    public Employee Login(Employee[] empDetails) {
        Employee emp = new Employee();
        int idFound;
        int correctPassword;
        int error;

        syspause.manySec(1);
        System.out.println("          AAA         BBBBBBBBB         CCCCCCCCCC");
        syspause.manySec(2);
        System.out.println("         AA AA        BB       BB     CC           CC");
        syspause.manySec(3);
        System.out.println("        AA   AA       BB        BB   CC");
        syspause.manySec(4);
        System.out.println("       AA     AA      BBBBBBBBBBB    CC");
        syspause.manySec(5);
        System.out.println("      AAAAAAAAAAA     BB        BB   CC");
        syspause.manySec(6);
        System.out.println("     AA         AA    BB        BB    CC           CC");
        syspause.manySec(7);
        System.out.println("    AAA         AAA   BBBBBBBBBBB       CCCCCCCCCC    ");
        syspause.manySec(8);

        do {
            Assignment.clearScreen();
            idFound = 0;
            correctPassword = 0;
            error = 1;
            System.out.println("          AAA         BBBBBBBBB         CCCCCCCCCC");
            System.out.println("         AA AA        BB       BB     CC           CC");
            System.out.println("        AA   AA       BB        BB   CC");
            System.out.println("       AA     AA      BBBBBBBBBBB    CC");
            System.out.println("      AAAAAAAAAAA     BB        BB   CC");
            System.out.println("     AA         AA    BB        BB    CC           CC");
            System.out.println("    AAA         AAA   BBBBBBBBBBB       CCCCCCCCCC    ");
            System.out.println("");

            System.out.print("Enter your Staff ID: ");
            id = input.nextLine();
            System.out.print("Enter your password: ");
            password = input.nextLine();

            for (Employee empDetail : empDetails) {
                if(id.equalsIgnoreCase(empDetail.getEmpID())){
                    idFound = 1;
                    if(password.equals(empDetail.getPassword())){
                        error = 0;
                        correctPassword = 1;
                        emp = empDetail;
                }
            }
            }
            
            if(idFound == 1){
                if(correctPassword == 1){
                    System.out.println("Login successful\n");
                }else{
                    System.err.println("Wrong Password entered\n");
                    System.out.flush();
                }
            }else{
                System.err.println("Staff ID entered does not exist\n");

            }
            
            System.out.println("Press Enter To Continue...");
            input.nextLine();
        } while (error == 1);
        
        
        
        return emp;
    }
    
}
