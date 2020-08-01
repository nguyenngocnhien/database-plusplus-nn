import services.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationStudent {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manage_student","root","nnhien166@@");
            System.out.println("Connection Success");
        } catch (SQLException e) {
            System.out.println("Connection Fail"+e);
        }
        StudentService studentService = new StudentService(connection);
        studentService.updateNoOfStudentInDepartments();
        studentService.updateAverageScore();
    }
}
