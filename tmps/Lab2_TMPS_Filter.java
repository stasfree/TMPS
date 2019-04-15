package ua.tmps;

import ua.tmps.Lab2_TMPS_Filter.Criteria;

import java.util.ArrayList;
import java.util.List;

public class Lab2_TMPS_Filter {
    public static class Employee {
        private String name;
        private String gender;
        private String position;

        public Employee(String name, String gender, String position) {
            this.name = name;
            this.gender = gender;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }

        public String getPosition() {
            return position;
        }
        //getters
    }

    public interface Criteria {
        public List<Employee> criteria(List<Employee> employeeList);
    }

    public static class CriteriaMale implements Criteria {

        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> maleEmployees = new ArrayList<>();

            for (Employee employee : employeeList) {
                if (employee.getGender().equalsIgnoreCase("Male")) {
                    maleEmployees.add(employee);
                }
            }
            return maleEmployees;
        }
    }

    public static class CriteriaFemale implements Criteria {

        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> femaleEmployees = new ArrayList<>();

            for (Employee employee : employeeList) {
                if (employee.getGender().equalsIgnoreCase("Female")) {
                    femaleEmployees.add(employee);
                }
            }
            return femaleEmployees;
        }
    }

    public static class CriteriaSenior implements Criteria {

        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> seniorEmployees = new ArrayList<>();

            for (Employee employee : employeeList) {
                if (employee.getPosition().equalsIgnoreCase("Senior")) {
                    seniorEmployees.add(employee);
                }
            }
            return seniorEmployees;
        }
    }

    public static class CriteriaJunior implements Criteria {

        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> juniorEmployees = new ArrayList<>();

            for (Employee employee : employeeList) {
                if (employee.getPosition().equalsIgnoreCase("Junior")) {
                    juniorEmployees.add(employee);
                }
            }
            return juniorEmployees;
        }
    }

    public static class AndCriteria implements Criteria {

        private Criteria firstCriteria;
        private Criteria secondCriteria;

        public AndCriteria(Criteria firstCriteria, Criteria secondCriteria) {
            this.firstCriteria = firstCriteria;
            this.secondCriteria = secondCriteria;
        }

        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
            return secondCriteria.criteria(firstCriteriaEmployees);
        }
    }

    public static class OrCriteria implements Criteria {
        private Criteria firstCriteria;
        private Criteria secondCriteria;

        public OrCriteria(Criteria firstCriteria, Criteria secondCriteria) {
            this.firstCriteria = firstCriteria;
            this.secondCriteria = secondCriteria;
        }


        @Override
        public List<Employee> criteria(List<Employee> employeeList) {
            List<Employee> firstCriteriaEmployees = firstCriteria.criteria(employeeList);
            List<Employee> secondCriteriaEmployees = secondCriteria.criteria(employeeList);

            for (Employee employee : secondCriteriaEmployees) {
                if (!firstCriteriaEmployees.contains(employee)) {
                    firstCriteriaEmployees.add(employee);
                }
            }
            return firstCriteriaEmployees;
        }
    }

    public static class Main {
        public static void main(String[] args) {
            List<Lab2_TMPS_Filter.Employee> employeeList = new ArrayList<>();

            //adding employees to the list
            employeeList.add(new Lab2_TMPS_Filter.Employee("David", "Male", "Senior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Scott", "Male", "Senior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Rhett", "Male", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Andrew", "Male", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Susan", "Female", "Senior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Rebecca", "Female", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Mary", "Female", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Juliette", "Female", "Senior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Jessica", "Female", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Mike", "Male", "Junior"));
            employeeList.add(new Lab2_TMPS_Filter.Employee("Chris", "Male", "Junior"));

            //initialization of the different criteria classes
            Criteria maleEmployees = new Lab2_TMPS_Filter.CriteriaMale();
            Criteria femaleEmployees = new Lab2_TMPS_Filter.CriteriaFemale();
            Criteria seniorEmployees = new Lab2_TMPS_Filter.CriteriaSenior();
            Criteria juniorEmployees = new Lab2_TMPS_Filter.CriteriaJunior();
            Criteria seniorFemale = new Lab2_TMPS_Filter.AndCriteria(seniorEmployees, femaleEmployees);
            Criteria juniorOrMale = new OrCriteria(juniorEmployees, maleEmployees);

            System.out.println("Male employees: ");
            printEmployeeInfo(maleEmployees.criteria(employeeList));

            System.out.println("\nFemale employees: ");
            printEmployeeInfo(femaleEmployees.criteria(employeeList));

            System.out.println("\nSenior female employees: ");
            printEmployeeInfo(seniorFemale.criteria(employeeList));

            System.out.println("\nJunior or male employees: ");
            printEmployeeInfo(juniorOrMale.criteria(employeeList));
        }


        //simple method to print out employee info
        public static void printEmployeeInfo(List<Lab2_TMPS_Filter.Employee> employeeList) {
            for (Lab2_TMPS_Filter.Employee employee : employeeList) {
                System.out.println("Employee info: | Name: "
                        + employee.getName() + ", Gender: "
                        + employee.getGender() + ", Position: "
                        + employee.getPosition() + " |");
            }
        }
    }
}



