import org.jetbrains.annotations.NotNull;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
record Department(String name, String code) { }
class Employee {
    private final String firstName;
    private final String lastName;
    private final Department department;
    private final String email;
    private final String password;
    public Employee(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = generateEmail();
        this.password = generateRandomPassword();
    }
    private @NotNull String generateEmail() {
        String company = "qwerty";
        return firstName.toLowerCase() + lastName.toLowerCase() + "@" + department.name().toLowerCase() + "." + company.toLowerCase() + ".com";
    }
    private @NotNull String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Department getDepartment() {
        return department;
    }
}
public class Main {
    public static void main(String[] args) {
        Department technical = new Department("Technical", "1");
        Department admin = new Department("Admin", "2");
        Department hr = new Department("Human Resource", "3");
        Department legal = new Department("Legal", "4");
        List<Department> departments = new ArrayList<>();
        departments.add(technical);
        departments.add(admin);
        departments.add(hr);
        departments.add(legal);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first name of the new hire: ");
        String firstName = scanner.next();
        System.out.print("Enter the last name of the new hire: ");
        String lastName = scanner.next();
        System.out.println("Department Codes:");
        for (Department department : departments)
            System.out.println(department.code() + "-" + department.name());
        System.out.print("Enter the department code of the new hire: ");
        String departmentCode = scanner.next();
        Department selectedDepartment = findDepartment(departments, departmentCode);
        if (selectedDepartment != null) {
            Employee newHire = new Employee(firstName, lastName, selectedDepartment);
            System.out.println("Generated email for the new hire: " + newHire.getEmail());
            System.out.println("Department: " + newHire.getDepartment().name());
            System.out.println("Generated password: " + newHire.getPassword());
        } else
            System.out.println("Invalid department code.");
        scanner.close();
    }
    private static Department findDepartment(List<Department> departments, String code) {
        for (Department department : departments) {
            if (department.code().equals(code))
                return department;
        }
        return null;
    }
}