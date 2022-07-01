import java.util.*;

public class Main {
    static factory fac = new factory();

    static Employee getEmployee( String empSecNum) throws EmployeeException {
        if(fac.getEmployees().containsKey(empSecNum)){
            return fac.getEmployees().get(empSecNum);
        }
        else{
            throw new EmployeeException(1);
        }
    }

    static Employee deleteEmployee(String empSecNum) throws EmployeeException {
        if(!fac.getEmployees().containsKey(empSecNum)){
            throw new EmployeeException(2);
        }
        else{
            Employee tem = fac.getEmployees().get(empSecNum);
            fac.getEmployees().remove(empSecNum);
            return tem;
        }
    }

    static Employee addEmployee(Employee emp) throws EmployeeException {
        if(fac.getEmployees().containsKey(emp.getSocialSecurityNumber())){
            throw new EmployeeException(3);
        }
        else{
            fac.getEmployees().put(emp.getSocialSecurityNumber(), emp);
            return emp;
        }
    }

    static Employee updateEmployee(String empSecNum ,Employee emp) throws EmployeeException {
        if(!fac.getEmployees().containsKey(empSecNum)){
            throw new EmployeeException(4);
        }
        else{
            fac.getEmployees().replace(empSecNum, emp);
            return emp;
        }
    }

    static void printEmployees(){
        fac.getEmployees().forEach((key, value) -> {
            value.toString();
        });
    }

    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        String[] cmd1 = cmd.split(" ");
        while(!cmd1[0].equals("exit")){
            switch(cmd1[0]){
                case "get":
                    try{
                        Employee value = getEmployee(cmd1[1]);
                        value.toString();
                    }catch (EmployeeException e){
                        System.out.println(e.code + "\n" + e.message);
                    }
                    break;
                case "add":
                    Employee employee = null;
                    switch(Integer.parseInt(cmd1[1])){
                        case 0:
                            employee = new SalaridEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((SalaridEmployee) employee).setWeeklySalary(Double.parseDouble(cmd1[5]));
                            break;
                        case 1:
                            employee = new HourlyEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((HourlyEmployee) employee).setWage(Double.parseDouble(cmd1[5]));
                            ((HourlyEmployee) employee).setHours(Double.parseDouble(cmd1[6]));
                            break;
                        case 2:
                            employee = new CommisionEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((CommisionEmployee) employee).setGrossSales(Double.parseDouble(cmd1[5]));
                            ((CommisionEmployee) employee).setCommissionRate(Double.parseDouble(cmd1[6]));
                            break;
                        case 3:
                            employee = new basePlusCommisionEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((basePlusCommisionEmployee)employee).setGrossSales(Double.parseDouble(cmd1[5]));
                            ((basePlusCommisionEmployee)employee).setCommissionRate(Double.parseDouble(cmd1[6]));
                            ((basePlusCommisionEmployee)employee).setBaseSalary(Double.parseDouble(cmd1[7]));
                    }
                    try{
                        Employee value = addEmployee(employee);
                        value.toString();
                    }catch (EmployeeException e){
                        System.out.println(e.code + "\n" + e.message);
                    }
                    break;
                case "update":
                    Employee employee1 = null;
                    switch(Integer.parseInt(cmd1[1])){
                        case 0:
                            employee1 = new SalaridEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((SalaridEmployee) employee1).setWeeklySalary(Double.parseDouble(cmd1[5]));
                            break;
                        case 1:
                            employee1 = new HourlyEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((HourlyEmployee) employee1).setWage(Double.parseDouble(cmd1[5]));
                            ((HourlyEmployee) employee1).setHours(Double.parseDouble(cmd1[6]));
                            break;
                        case 2:
                            employee1 = new CommisionEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((CommisionEmployee) employee1).setGrossSales(Double.parseDouble(cmd1[5]));
                            ((CommisionEmployee) employee1).setCommissionRate(Double.parseDouble(cmd1[6]));
                            break;
                        case 3:
                            employee1 = new basePlusCommisionEmployee(cmd1[2], cmd1[3], cmd1[4]);
                            ((basePlusCommisionEmployee)employee1).setGrossSales(Double.parseDouble(cmd1[5]));
                            ((basePlusCommisionEmployee)employee1).setCommissionRate(Double.parseDouble(cmd1[6]));
                            ((basePlusCommisionEmployee)employee1).setBaseSalary(Double.parseDouble(cmd1[7]));
                    }
                    try{
                        Employee value = updateEmployee(cmd1[4], employee1);
                        value.toString();
                    }catch (EmployeeException e){
                        System.out.println(e.code + "\n" + e.message);
                    }
                    break;
                case "delete":
                    try{
                        Employee value = deleteEmployee(cmd1[1]);
                        value.toString();
                    }catch (EmployeeException e){
                        System.out.println(e.code + "\n" + e.message);
                    }
                    break;
                case "print":
                    printEmployees();
                    break;
            }
            cmd = in.nextLine();
            cmd1 = cmd.split(" ");
        }
    }
}

abstract class Employee implements Comparable<Employee>{
    private String firstName, lastName, socialSecurityNumber;

    Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public abstract double earning();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString(){
        System.out.print("firstName:" + firstName + "; lastName:" + lastName + "; socialSecurityNumber:" + socialSecurityNumber + "; earning:");
        System.out.printf("%.2f\n", earning());
        return null;
    }

    @Override
    public int compareTo(Employee employee){
        return Double.compare(this.earning(), employee.earning());
    }
}

class SalaridEmployee extends Employee{
    private double weeklySalary;

    SalaridEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double earning() {
        return weeklySalary * 4;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }


}

class HourlyEmployee extends Employee{
    double wage, hours;

    HourlyEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double earning(){
        return wage * hours;
    }

    public double getWage() {
        return wage;
    }

    public double getHours() {
        return hours;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}

class CommisionEmployee extends Employee{
    private double grossSales, commissionRate;

    CommisionEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double earning() {
        return grossSales * commissionRate;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}

class basePlusCommisionEmployee extends CommisionEmployee{
    private double baseSalary;

    basePlusCommisionEmployee(String firstName, String lastName, String socialSecurityNumber) {
        super(firstName, lastName, socialSecurityNumber);
    }

    public double earning() {
        return getGrossSales() * getCommissionRate() + baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
}

class EmployeeException extends Exception{
    String code;
    String message;
    EmployeeException(int n){
        switch (n){
            case 1:
                code = "1004";
                message = "employee not found.";
                break;
            case 2:
                code = "1002";
                message = "employee not found.";
                break;
            case 3:
                code = "1001";
                message = "employee exists.";
                break;
            case 4:
                code = "1003";
                message = "employee not found.";
        }
    }
}

class factory{
    private TreeMap<String, Employee> employees = new TreeMap<>();
    TreeMap<String, Employee> getEmployees(){
        return employees;
    }
}