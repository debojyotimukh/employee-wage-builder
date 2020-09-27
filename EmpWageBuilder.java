class Company {
    private int empRatePerHr;
    private int workingDaysPerMonth;
    private int maxWorkingHrs;

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
}

class Employee {
    public static final int IS_FULL_TIME = 2;
    public static final int IS_PART_TIME = 1;

    Company company;

    Employee(Company company) {
        this.company = company;
    }

    public int calculateWage() {
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
            empWage = empHrs *company.getWage();
            totalWage += empWage;
        }
        return totalWage;
    }
}

public class EmpWageBuilder {

    public static void main(String[] args) {
        System.out.println("Welcome to Employee wage computation program");

        Company capg=new Company(20, 20, 100);
        Employee e1 = new Employee(capg);
        System.out.println("Emp Wage for Emp1 of capg: " + e1.calculateWage());
        Company infy=new Company(18, 20, 160);
        Employee e2=new Employee(infy);
        System.out.println("Emp Wage for Emp2 of infy: " + e2.calculateWage());
    }
}