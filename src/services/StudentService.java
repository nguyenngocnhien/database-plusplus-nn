package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private Connection con;

    public StudentService() {
    }

    public StudentService(Connection con) {
        this.con = con;
    }

    public void updateNoOfStudentInDepartments() {
        try {
            Statement stmt = con.createStatement();
            String sql = "select DeptID,count(StudentID) as Quantity from students group by DeptID";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String deptID = rs.getString(1);
                Integer noOfStudents = rs.getInt(2);

                String query = "Update departments set NoOfStudents=" + noOfStudents + " WHERE  DeptID='" + deptID + "'";
                Statement sts = con.createStatement();
                sts.executeUpdate(query);
            }
            String qr = "UPDATE departments set NoOfStudents=0 WHERE NoOfStudents is null";
            stmt.executeUpdate(qr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAverageScore() {
        try {
            Statement stmtAVG = con.createStatement();
            String qr1 = "SELECT DISTINCT StudentID FROM results";
            ResultSet rs = stmtAVG.executeQuery(qr1);
            List<String> studentID = new ArrayList<>();
            while (rs.next()) {
                studentID.add(rs.getString(1));
            }
            String getCourseID_Credits = "SELECT CourseID,Credits FROM courses";
            rs = stmtAVG.executeQuery(getCourseID_Credits);
            Map<String, Integer> mapCourse_Credits = new HashMap<>();
            while (rs.next()) {
                mapCourse_Credits.put(rs.getString(1), rs.getInt(2));
            }
            for (String std : studentID
            ) {
                String qr = "SELECT CourseID,Mark FROM results WHERE StudentID ='"+std+"'";
                rs = stmtAVG.executeQuery(qr);
                Map<String,Float> mapCouresID_Mark = new HashMap<>();
                while (rs.next()){
                    String couresID = rs.getString(1);
                    Float mark = rs.getFloat(2);
                    Float value = mapCouresID_Mark.get(couresID);
                    if (value==null){
                        mapCouresID_Mark.put(couresID,mark);
                    }
                    else if (value < mark){
                        mapCouresID_Mark.put(couresID,mark);
                    }
                }
                Float total = 0.0f;
                Integer creditsTotal =0;
                for (String courseID: mapCouresID_Mark.keySet()){
                    Float mark = mapCouresID_Mark.get(courseID);
                    Integer credit = mapCourse_Credits.get(courseID);
                    total = total + mark * credit;
                    creditsTotal +=credit;
                }
                Float avgScore = total/creditsTotal;
                String updateStudent = "UPDATE students SET AverageScore = '" + avgScore + "' WHERE StudentID = '" + std + "'";
                Statement updateSt = con.createStatement();
                updateSt.executeUpdate(updateStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
