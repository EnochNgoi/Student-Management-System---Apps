package com.example.studentmanagementapp;

public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String course;

    public Student(int id, String name, int age, String gender, String course) {
        this.id = id; // `this` refers to the current instance of the class
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
    }

    // Getters - access the value
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }

    // Setters - modify the value
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Gender='" + gender + '\'' +
                ", Course='" + course + '\'' +
                '}';
    }
}
