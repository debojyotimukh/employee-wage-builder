import java.util.ArrayList;
import java.util.List;

class Company {
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

    public int totalSalaryExpence() {
        if (totalSalary != -1)
            return totalSalary;
        int totalSalary = 0;
        for (Employee e : emplist)
            totalSalary += e.calculateWage();

        return totalSalary;
    }
}

class Employee {
    public static final int IS_FULL_TIME = 2;
    public static final int IS_PART_TIME = 1;

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
    public static void main(String[] args) {
        System.out.println("Welcome to Employee wage computation program");

        Company[] companies = new Company[2];

        Company capg = new Company(20, 20, 100);
        companies[0] = capg;
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        Employee e3 = new Employee();
        companies[0].hire(e1);
        companies[0].hire(e2);
        companies[0].hire(e3);
        System.out.println("Total emp Wage for capg: " + companies[0].totalSalaryExpence());

        Company infy = new Company(18, 20, 160);
        companies[1] = infy;
        Employee e4 = new Employee();
        Employee e5 = new Employee();
        companies[1].hire(e4);
        companies[1].hire(e5);
        System.out.println("Total emp Wage for infy: " + companies[1].totalSalaryExpence());
    }
}