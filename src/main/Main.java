package main;

import model.Student;
import model.Course;
import service.TrackerService;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TrackerService tracker = new TrackerService();
        Scanner scanner = new Scanner(System.in); // 

        System.out.println("=== Система учёта успеваемости студентов ===");

        while (true) { // 
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Добавить студента"); // [cite: 31]
            System.out.println("2. Добавить предмет"); // [cite: 32]
            System.out.println("3. Выставить оценку"); // [cite: 33]
            System.out.println("4. Показать средний балл студента"); // [cite: 34]
            System.out.println("5. Показать топ-3 студентов"); // [cite: 35]
            System.out.println("6. Аналитика по предметам"); // [cite: 36]
            System.out.println("7. Выход"); // [cite: 37]
            System.out.print("Выберите действие (1-7): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // [cite: 31]
                    try {
                        System.out.print("Введите ID студента (число): ");
                        int id = Integer.parseInt(scanner.nextLine()); // [cite: 43]

                        if (tracker.getStudent(id) != null) {
                            System.out.println("Ошибка: Студент с таким ID уже существует.");
                            break;
                        }

                        System.out.print("Введите имя студента: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите группу: ");
                        String group = scanner.nextLine();

                        tracker.addStudent(new Student(id, name, group));
                        System.out.println("Студент успешно добавлен!");
                    } catch (NumberFormatException e) { // [cite: 43]
                        System.out.println("Ошибка ввода! ID должен быть целым числом."); // [cite: 43]
                    }
                    break;

                case "2": // [cite: 32]
                    System.out.print("Введите код предмета (напр. INF-101): ");
                    String code = scanner.nextLine();
                    System.out.print("Введите название предмета: ");
                    String courseName = scanner.nextLine();

                    tracker.addCourse(new Course(code, courseName));
                    System.out.println("Предмет успешно добавлен!");
                    break;

                case "3": // [cite: 33]
                    try {
                        System.out.print("Введите ID студента: ");
                        int studentId = Integer.parseInt(scanner.nextLine()); // [cite: 43]
                        System.out.print("Введите код предмета: ");
                        String courseCode = scanner.nextLine();
                        System.out.print("Введите оценку (0-100): ");
                        int score = Integer.parseInt(scanner.nextLine()); // [cite: 43]

                        tracker.assignGrade(studentId, courseCode, score);
                    } catch (NumberFormatException e) { // [cite: 43]
                        System.out.println("Ошибка ввода! ID и оценка должны быть целыми числами."); // [cite: 43]
                    }
                    break;

                case "4": // [cite: 34]
                    try {
                        System.out.print("Введите ID студента: ");
                        int studentId = Integer.parseInt(scanner.nextLine()); // [cite: 43]

                        Student student = tracker.getStudent(studentId);
                        if (student == null) {
                            System.out.println("Ошибка: Студент с таким ID не найден."); // [cite: 42]
                        } else {
                            double gpa = tracker.getStudentGpa(studentId);
                            System.out.println("Cтудент: " + student.getName() + " | GPA: " + String.format("%.2f", gpa));
                        }
                    } catch (NumberFormatException e) { // [cite: 43]
                        System.out.println("Ошибка ввода! ID должен быть числом."); // [cite: 43]
                    }
                    break;

                case "5": // [cite: 35]
                    System.out.println("=== ТОП-3 СТУДЕНТОВ ===");
                    List<Student> topStudents = tracker.getTop3Students();
                    if (topStudents.isEmpty()) {
                        System.out.println("В базе данных пока нет студентов.");
                    } else {
                        for (int i = 0; i < topStudents.size(); i++) {
                            Student s = topStudents.get(i);
                            System.out.println((i + 1) + ". " + s.getName() + " (ID: " + s.getId() + ") — GPA: " + String.format("%.2f", tracker.getStudentGpa(s.getId())));
                        }
                    }
                    break;

                case "6": // [cite: 36]
                    System.out.println("=== СРЕДНИЙ БАЛЛ ПО ПРЕДМЕТАМ ===");
                    tracker.printCoursesAnalytics();
                    break;

                case "7": // [cite: 37]
                    System.out.println("Выход из программы. Хорошего дня!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный пункт меню. Попробуйте снова (1-7).");
                    break;
            }
        }
    }
}