package model;

public class Course {
    private String courseCode; // [cite: 15, 17]
    private String courseName; // [cite: 15, 17]

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    // Геттеры и Сеттеры 
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    @Override
    public String toString() { // 
        return "Предмет [Код: " + courseCode + ", Название: " + courseName + "]";
    }
}