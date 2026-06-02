package model;

public class Grade {
    private int studentId; // [cite: 16, 17]
    private String courseCode; // [cite: 16, 17]
    private int score; // [cite: 16, 17]

    public Grade(int studentId, String courseCode, int score) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.score = score;
    }

    // Геттеры и Сеттеры 
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    @Override
    public String toString() { // 
        return "Оценка [ID Студента: " + studentId + ", Код Предмета: " + courseCode + ", Балл: " + score + "]";
    }
}