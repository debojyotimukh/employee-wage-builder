public class EmpWageBuilder {
    public static final int EMP_RATE_PER_HOUR = 20;
    public static final int IS_FULL_TIME = 2;
    public static final int IS_PART_TIME = 1;
    public static final int WORKING_DAY_MONTH = 20;
    public static final int MAX_WORKING_HRS = 100;

    public static void main(String[] args) {
        System.out.println("Welcome to Employee wage computation program");

        int totalWage = 0;
        int totalHrs = 0;
        for (int day = 0; day < WORKING_DAY_MONTH && totalHrs < MAX_WORKING_HRS; day++) {
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
            empWage = empHrs * EMP_RATE_PER_HOUR;
            totalWage += empWage;
        }
        System.out.println("Emp Wage: " + totalWage);
    }
}