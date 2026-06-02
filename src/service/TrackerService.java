package service;

import model.Student;
import model.Course;
import model.Grade;
import java.util.*;

public class TrackerService {
    // Используем структуры данных по рекомендациям из ТЗ [cite: 39, 40]
    private final Map<Integer, Student> studentMap = new HashMap<>(); // [cite: 40]
    private final List<Course> courseList = new ArrayList<>(); // [cite: 39]
    private final List<Grade> gradeList = new ArrayList<>();

    // --- ТАСК 2: Базовые методы --- [cite: 18]

    public void addStudent(Student student) { // [cite: 20]
        studentMap.put(student.getId(), student);
    }

    public void addCourse(Course course) { // [cite: 21]
        courseList.add(course);
    }

    public boolean assignGrade(int studentId, String courseCode, int score) { // [cite: 22]
        // Проверяем, существует ли студент [cite: 42]
        if (!studentMap.containsKey(studentId)) {
            System.out.println("Ошибка: Студент с ID " + studentId + " не существует."); // [cite: 42]
            return false;
        }

        // Проверяем, существует ли предмет
        boolean courseExists = false;
        for (Course c : courseList) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                courseExists = true;
                break;
            }
        }
        if (!courseExists) {
            System.out.println("Ошибка: Предмет с кодом " + courseCode + " не найден.");
            return false;
        }

        // Валидация оценки [cite: 41]
        if (score < 0 || score > 100) {
            System.out.println("Ошибка: Оценка должна быть строго от 0 до 100."); // [cite: 41]
            return false;
        }

        gradeList.add(new Grade(studentId, courseCode, score));
        System.out.println("Оценка успешно выставлена!");
        return true;
    }

    // Расчет среднего балла (GPA) студента [cite: 23]
    public double getStudentGpa(int studentId) {
        int sum = 0;
        int count = 0;
        for (Grade grade : gradeList) {
            if (grade.getStudentId() == studentId) {
                sum += grade.getScore();
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) sum / count;
    }

    // --- ТАСК 3: Аналитика и поиск --- [cite: 24, 25]

    // Список всех студентов, у которых GPA выше заданного порога [cite: 26]
    public List<Student> getStudentsWithGpaAbove(double threshold) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (getStudentGpa(student.getId()) > threshold) {
                result.add(student);
            }
        }
        return result;
    }

    // Топ-3 студентов с наивысшей успеваемостью [cite: 27]
    public List<Student> getTop3Students() {
        List<Student> allStudents = new ArrayList<>(studentMap.values());
        // Сортируем по убыванию GPA
        allStudents.sort((s1, s2) -> Double.compare(getStudentGpa(s2.getId()), getStudentGpa(s1.getId())));
        // Возвращаем топ-3 (или меньше, если студентов в базе мало)
        return allStudents.subList(0, Math.min(3, allStudents.size()));
    }

    // Список предметов и средний балл по каждому из них [cite: 28]
    public void printCoursesAnalytics() {
        if (courseList.isEmpty()) {
            System.out.println("Список предметов пуст.");
            return;
        }
        for (Course course : courseList) {
            int sum = 0;
            int count = 0;
            for (Grade grade : gradeList) {
                if (grade.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                    sum += grade.getScore();
                    count++;
                }
            }
            double average = count == 0 ? 0.0 : (double) sum / count;
            System.out.println(course.getCourseName() + " (" + course.getCourseCode() + ") — Средний балл: " + String.format("%.2f", average));
        }
    }

    // Вспомогательный метод для проверки существования студента в Main
    public Student getStudent(int id) {
        return studentMap.get(id);
    }
}