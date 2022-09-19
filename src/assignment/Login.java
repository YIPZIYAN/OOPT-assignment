//package assignment;
//
//import java.util.HashMap;
//import java.util.Scanner;
//
///**
// *
// * @Vallerie
// */
//public class Login {
//
//    HashMap<String, String> idPassword = new HashMap<>();
//    Scanner input = new Scanner(System.in);
//    private String id;
//    private String password;
//
//    public Login() {
//
//    }
//
//    public HashMap<Boolean, String> Login(HashMap<String, String> idPassword) throws InterruptedException {
//
//        int error = 0;
//        syspause.manySec(1);
//        System.out.println("          AAA         BBBBBBBBB         CCCCCCCCCCC");
//        syspause.manySec(1);
//        System.out.println("         AA AA        BB       BB     CC           CC");
//        syspause.manySec(1);
//        System.out.println("        AA   AA       BB        BB   CC");
//        syspause.manySec(1);
//        System.out.println("       AA     AA      BBBBBBBBBBB    CC");
//        syspause.manySec(1);
//        System.out.println("      AAAAAAAAAAA     BB        BB   CC");
//        syspause.manySec(1);
//        System.out.println("     AA         AA    BB        BB    CC           CC");
//        syspause.manySec(1);
//        System.out.println("    AAA         AAA   BBBBBBBBBBB       CCCCCCCCCCC    ");
//        System.out.println("");
//        syspause.manySec(1);
//
//        do {
//            System.out.print("Enter your Staff ID: ");
//            id = input.nextLine();
//            System.out.print("Enter your password: ");
//            password = input.nextLine();
//
//            HashMap<String, String> inputIDPass = new HashMap<String, String>();
//            inputIDPass.put(id, password);
//
//            if (idPassword.containsKey(id.toUpperCase())) {                     //check whether the entered id is valid
//                if (idPassword.get(id.toUpperCase()).equals(password)) {        //check the id matches the password
//                    System.out.println("Login successful");
//                    error = 0;
//                    HashMap<Boolean, String> a = new HashMap<>();
//                    a.put(true, id.toUpperCase());
//                    return a;
//                } else {
//                    System.out.println("Wrong password entered\n");
//                    syspause.oneSec();
//                    error = 1;
//                    HashMap<Boolean, String> a = new HashMap<>();
//                    a.put(false, null);
//                    return a;
//                }
//            } else {
//                System.out.println("Please enter a valid Staff ID\n");
//                error = 1;
//                syspause.oneSec();
//                return false;
//            }
//        } while (error != 0);
//
//    }
//}
