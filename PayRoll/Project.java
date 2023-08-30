import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Project {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and show the login frame
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}

class LoginFrame extends JFrame {
    private JButton userLoginButton;
    private JButton adminLoginButton;
    private JButton userSignUpButton;

    public LoginFrame() {
        setTitle("Employee Payroll System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        userLoginButton = new JButton("User Login");
        adminLoginButton = new JButton("Admin Login");
        userSignUpButton = new JButton("Sign Up");
        
        panel.add(userLoginButton);
        panel.add(adminLoginButton);
        panel.add(userSignUpButton);

        userLoginButton.addActionListener(e -> {
            // Implement user login logic
            // Show user interface after successful login
        });

        adminLoginButton.addActionListener(e -> {
            // Implement admin login logic
            // Show admin interface after successful login
        });

        userSignUpButton.addActionListener(e -> {
            UserSignUpFrame userSignUpFrame = new UserSignUpFrame();
            userSignUpFrame.setVisible(true);
        });

        add(panel);
    }
}

class UserSignUpFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JButton signUpButton;

    public UserSignUpFrame() {
        setTitle("User Sign-Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        passwordField = new JPasswordField();
        signUpButton = new JButton("Sign Up");

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(signUpButton);

        signUpButton.addActionListener(e -> {
            // Implement user registration logic
            // Insert user data into the database
        });

        add(panel);
    }
}

class AdminFrame extends JFrame {
    private JButton viewEmployeesButton;
    private JButton addEmployeeButton;
    private JButton updateEmployeeButton;
    private JButton deleteEmployeeButton;

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        viewEmployeeButton = new JButton("View Employee");
        addEmployeeButton = new JButton("Add Employee");
        updateEmployeeButton = new JButton("Update Employee");
        deleteEmployeeButton = new JButton("Delete Employee");

        panel.add(viewEmployeesButton);
        panel.add(addEmployeeButton);
        panel.add(updateEmployeeButton);
        panel.add(deleteEmployeeButton);

        viewEmployeesButton.addActionListener(e -> {
            // Implement logic to view employees
        });

        addEmployeeButton.addActionListener(e -> {
            // Implement logic to add employee
        });

        updateEmployeeButton.addActionListener(e -> {
            // Implement logic to update employee
        });

        deleteEmployeeButton.addActionListener(e -> {
            // Implement logic to delete employee
        });

        add(panel);
    }
}

class EmployeeCRUD {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_payroll_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Create new employee
    public static void createEmployee(String firstName, String lastName, double salary) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO employee (Id, Name, Department, Salary, Deduction, Overtime, Bonuses) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, Id);
            statement.setString(2, Name);
            statement.setString(3, Department);
            statement.setInt(4, Salary);
            statement.setInt(5, Deduction);
            statement.setInt(6, Overtime);
            statement.setInt(7, Bonuses);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read employee data (for users)
    public static void readEmployeeForUsers() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name FROM employee")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read employee data (for admin)
    public static void readEmployeeForAdmin() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employee")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                double salary = resultSet.getDouble("salary");
                System.out.println("ID: " + id + ", Name: " + firstName + " " + lastName + ", Salary: " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update employee data
    public static void updateEmployeeSalary(int employeeId, double newSalary) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employee SET salary = ? WHERE id = ?")) {
            statement.setDouble(1, newSalary);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete employee
    public static void deleteEmployee(int employeeId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id = ?")) {
            statement.setInt(1, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_payroll_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
