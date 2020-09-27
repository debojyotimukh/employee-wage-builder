import java.util.ArrayList;
import java.util.List;

/**
 * Wage
 */
interface Wage {
    public static final int IS_FULL_TIME = 2;
    public static final int IS_PART_TIME = 1;

    public int calculateWage();
}

class Company implements Wage{
    private int empRatePerHr;
    private int workingDaysPerMonth;
    private int maxWorkingHrs;
    private List<Employee> emplist = new ArrayList<>();
    private int totalSalary = -1;

    public Company(int wage, int workingDays, int maxWorkingHrs) {
        this.empRatePerHr = wage;
        this.workingDaysPerMonth = workingDays;
        this.maxWorkingHrs = maxWorkingHrs;
    }

    public int getWage() {
        return empRatePerHr;
    }

    public int getMaxDays() {
        return workingDaysPerMonth;
    }

    public int getMaxHrs() {
        return maxWorkingHrs;
    }

    public void hire(Employee e) {
        e.setCompany(this);
        emplist.add(e);
    }

    public void fire(Employee e) {
        e.setCompany(null);
        emplist.remove(e);
    }

    public int calculateWage() {
        if (totalSalary != -1)
            return totalSalary;
        int totalSalary = 0;
        for (Employee e : emplist)
            totalSalary += e.calculateWage();

        return totalSalary;
    }
}

class Employee implements Wage{

    Company company;

    Employee() {
    };

    Employee(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int calculateWage() {
        if (company == null) // if unemployed
            return 0;

        int totalWage = 0;
        int totalHrs = 0;
        for (int day = 0; day < company.getMaxDays() && totalHrs < company.getMaxHrs(); day++) {
            int empHrs = 0;
            int empWage = 0;
            int empCheck = (int) Math.floor(Math.random() * 10) % 3;
            switch (empCheck) {
                case IS_FULL_TIME:
                    empHrs = 8;
                    break;
                case IS_PART_TIME:
                    empHrs = 4;
                    break;
                default:
                    empHrs = 0;
            }
            totalHrs += empHrs;
            empWage = empHrs * company.getWage();
            totalWage += empWage;
        }
        return totalWage;
    }
}

public class EmpWageBuilder {
    private static List<Employee> employees = new ArrayList<>();
    private static List<Company> companies = new ArrayList<>();

    public static void newEmployee(Employee e, Company parent) {
        parent.hire(e);
        e.setCompany(parent);
        employees.add(e);
    }

    public static void newCompany(Company company) {
        companies.add(company);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Employee wage computation program");

        Company capg = new Company(20, 20, 100);
        newCompany(capg);
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        Employee e3 = new Employee();
        newEmployee(e1, capg);
        newEmployee(e2, capg);
        newEmployee(e3, capg);
        System.out.println("Total emp Wage for capg: " + capg.calculateWage());
        
        Company infy = new Company(18, 20, 160);
        newCompany(infy);
        Employee e4 = new Employee();
        Employee e5 = new Employee();
        newEmployee(e4, infy);
        newEmployee(e5, infy);
        System.out.println("Total emp Wage for infy: " + infy.calculateWage());
    }
}