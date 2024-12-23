package com.example.studentmanagementapp;

public class Student {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String course;

    public Student(int id, String name, int age, String gender, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getCourse() { return course; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCourse(String course) { this.course = course; }

    // Overriding toString() to provide a readable student representation
    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Age: " + age + "\n"
                + "Gender: " + gender + "\n"
                + "Course: " + course + "\n";
    }
}
