package assignment;

import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @Vallerie 
 */
public class Login {
    HashMap<String, String> idPassword = new HashMap<String, String>();
    Scanner input = new Scanner(System.in);
    private String id;
    private String password;
    
    public Login(){
        this.idPassword= idPassword;
    }
    
    public Boolean Login(HashMap<String, String> idPassword) throws InterruptedException {
        
        int error = 0;
        Thread.sleep(100);
        System.out.println("          AAA         BBBBBBBBB         CCCCCCCCCCC");
        Thread.sleep(200);
        System.out.println("         AA AA        BB       BB     CC           CC");
        Thread.sleep(300);
        System.out.println("        AA   AA       BB        BB   CC");
        Thread.sleep(400);
        System.out.println("       AA     AA      BBBBBBBBBBB    CC");
        Thread.sleep(500);
        System.out.println("      AAAAAAAAAAA     BB        BB   CC");
        Thread.sleep(600);
        System.out.println("     AA         AA    BB        BB    CC           CC");
        Thread.sleep(700);
        System.out.println("    AAA         AAA   BBBBBBBBBBB       CCCCCCCCCCC    ");
        System.out.println("");
        Thread.sleep(100);
        do{
            System.out.print("Enter your Staff ID: ");
            id = input.nextLine();
            System.out.print("Enter your password: ");
            password = input.nextLine();
        
            HashMap<String, String> inputIDPass = new HashMap<String, String>();
            inputIDPass.put(id, password);
        
            if(idPassword.containsKey(id.toUpperCase())){                     //check whether the entered id is valid
                if(idPassword.get(id.toUpperCase()).equals(password)){        //check the id matches the password
                    System.out.println("Login successful");
                    error = 0;
                    return true;
                }
                else{
                    System.out.println("Wrong password entered\n");
                    syspause.oneSec();                    
                    error = 1;
                    return false;
                }
            }
            else{
                System.out.println("Please enter a valid Staff ID\n");
                error = 1;
                syspause.oneSec();
                return false;
            }
        }while(error != 0);
    
    }
    }
