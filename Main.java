import java.util.*;

public class Main {
    static public void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        String n = cin.nextLine();
        Employee[] employees = new Employee[Integer.parseInt(n)];
        for(int i = 0; i < Integer.parseInt(n); i++){
            String mes = cin.nextLine();
            String[] mes1 = mes.split(" ");
            switch(Integer.parseInt(mes1[0])){
                case 0:
                    SalaridEmployee employee = new SalaridEmployee(mes1[1], mes1[2], mes1[3]);
                    employee.setWeeklySalary(Double.parseDouble(mes1[4]));
                    employees[i] = employee;
                    break;
                case 1:
                    HourlyEmployee employee1 = new HourlyEmployee(mes1[1], mes1[2], mes1[3]);
                    employee1.setWage(Double.parseDouble(mes1[4]));
                    employee1.setHours(Double.parseDouble(mes1[5]));
                    employees[i] = employee1;
                    break;
                case 2:
                    CommisionEmployee employee2 = new CommisionEmployee(mes1[1], mes1[2], mes1[3]);
                    employee2.setGrossSales(Double.parseDouble(mes1[4]));
                    employee2.setCommissionRate(Double.parseDouble(mes1[5]));
                    employees[i] = employee2;
                    break;
                case 3:
                    basePlusCommisionEmployee employee3 = new basePlusCommisionEmployee(mes1[1], mes1[2], mes1[3]);
                    employee3.setGrossSales(Double.parseDouble(mes1[4]));
                    employee3.setCommissionRate(Double.parseDouble(mes1[5]));
                    employee3.setBaseSalary(Double.parseDouble(mes1[6]));
                    employees[i] = employee3;
                    break;
            }
        }
        Arrays.sort(employees);
        String m = cin.nextLine();
        for(int i = 0; i < Integer.parseInt(m); i++){
            String test = cin.nextLine();
            String[] test1 = test.split(" ");
            if(test1[0].equals("0")){
                for(int j = 0; j < Integer.parseInt(n); j++){
                    if(employees[j].getFirstName().equals(test1[1])){
                        employees[j].toString();
                    }
                }
            }
            else if(test1[0].equals("1")){
                for(int j = 0; j < Integer.parseInt(n); j++){
                    if(employees[j].getSocialSecurityNumber().equals(test1[1])){
                        employees[j].toString();
                    }
                }
            }
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
