package services;

import models.Students;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Connection con;
    public StudentService() {
    }
    public StudentService(Connection con) {
        this.con = con;
    }
    public List<Students> updateNoOfStudentForDepartments(){
        List<Students> stdArr = new ArrayList<>();
        Students students;
        try {
            Statement stmt = con.createStatement();
            String sql="select DeptID,count(StudentID) as Quantity from students group by DeptID";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                students = new Students(
                        rs.getString(1),
                        rs.getInt(2)
                );
                stdArr.add(students);
            }
            for (int i = 0;i<stdArr.size();i++){
                String query = "Update departments set NoOfStudents="+stdArr.get(i).getQuantity()+" WHERE  DeptID='"+stdArr.get(i).getDepID()+"'";
                int rss = stmt.executeUpdate(query);
            }
            String qr = "UPDATE departments set NoOfStudents=0 WHERE NoOfStudents is null";
            int ex = stmt.executeUpdate(qr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stdArr;
    }
}
