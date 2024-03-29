package assignment;

public class Employee {
    private String empID;
    private String name;
    private char gender;
    private String contactNo;
    private String password;
    private String position;
    private double salary;
    private static int empCount = 0;

    public Employee() {
        this.empID = "A1" + String.format("%03d", empCount);
        this.name = "";
        this.gender = 0;
        this.contactNo = "";
        this.password = "";
        this.position = "";
        this.salary = 0.00;
        empCount++;
    }

    public Employee(String name, char gender, String contactNo, String password, String position, double salary) {
        this.empID = "A1" + String.format("%03d", empCount);
        this.name = name;
        this.gender = gender;
        this.contactNo = contactNo;
        this.password = password;
        this.position = position;
        this.salary = salary;
        empCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmpID() {
        return empID;
    }

    public static int getEmpCount() {
        return empCount;
    }
    
    
    @Override
    public String toString(){
        return String.format("|  %-6s|        %-12s|  %-7s|  %-11s| %-9s|   %-9s|\n", empID, name, gender, contactNo, position, salary);
    }

   
   
    
    
}
