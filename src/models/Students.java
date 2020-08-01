package models;

public class Students {
    private String StudentID, LastName, FirtName, Sex, DateOfBith, PlaceOfBith, DepID;
    private int Scholarship, AverageScore;

    public Students() {
    }


    public Students(String studentID, String lastName, String firtName, String sex, String dateOfBith, String placeOfBith, String depID, int scholarship, int averageScore) {
        StudentID = studentID;
        LastName = lastName;
        FirtName = firtName;
        Sex = sex;
        DateOfBith = dateOfBith;
        PlaceOfBith = placeOfBith;
        DepID = depID;
        Scholarship = scholarship;
        AverageScore = averageScore;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirtName() {
        return FirtName;
    }

    public void setFirtName(String firtName) {
        FirtName = firtName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDateOfBith() {
        return DateOfBith;
    }

    public void setDateOfBith(String dateOfBith) {
        DateOfBith = dateOfBith;
    }

    public String getPlaceOfBith() {
        return PlaceOfBith;
    }

    public void setPlaceOfBith(String placeOfBith) {
        PlaceOfBith = placeOfBith;
    }

    public String getDepID() {
        return DepID;
    }

    public void setDepID(String depID) {
        DepID = depID;
    }

    public int getScholarship() {
        return Scholarship;
    }

    public void setScholarship(int scholarship) {
        Scholarship = scholarship;
    }

    public int getAverageScore() {
        return AverageScore;
    }

    public void setAverageScore(int averageScore) {
        AverageScore = averageScore;
    }

}
