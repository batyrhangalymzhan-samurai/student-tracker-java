package model;

public class Student {
    private int id; // [cite: 14, 17]
    private String name; // [cite: 14, 17]
    private String group; // [cite: 14, 17]

    public Student(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    // Геттеры и Сеттеры 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    @Override
    public String toString() { // 
        return "Студент [ID: " + id + ", Имя: " + name + ", Группа: " + group + "]";
    }
}