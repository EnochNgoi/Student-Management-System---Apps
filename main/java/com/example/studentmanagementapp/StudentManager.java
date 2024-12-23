package com.example.studentmanagementapp;

public class StudentManager {
        private Student[] students;
        private int studentCount;
        private final int MAX_STUDENTS;

        public StudentManager(int maxStudents) {
            this.MAX_STUDENTS = maxStudents;
            this.students = new Student[maxStudents];
            this.studentCount = 0;
        }

        // Add a student
        public boolean addStudent(Student student) {
            if (studentCount >= MAX_STUDENTS) {
                return false;
            }
            students[studentCount++] = student;
            return true;
        }

        // Delete a student by ID
        public boolean deleteStudent(int id) {
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    for (int j = i; j < studentCount - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[--studentCount] = null;
                    return true;
                }
            }
            return false;
        }

        // Update student details by ID
        public boolean updateStudent(int id, String name, int age, String gender, String course) {
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    students[i].setName(name);
                    students[i].setAge(age);
                    students[i].setGender(gender);
                    students[i].setCourse(course);
                    return true;
                }
            }
            return false;
        }

    // Display all students
    public String displayAllStudents() {
        if (studentCount == 0) {
            return "No students to display.";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Students:\n\n");
        for (int i = 0; i < studentCount; i++) {
            builder.append(students[i].toString()).append("\n");
        }
        return builder.toString();
    }

        // Search for a student by ID
        public Student searchStudentById(int id) {
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    return students[i];
                }
            }
            return null;
        }

        // Sort students by name
        public void sortStudentsByName() {
            for (int i = 0; i < studentCount - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < studentCount; j++) {
                    if (students[j].getName().compareToIgnoreCase(students[minIndex].getName()) < 0) {
                        minIndex = j;
                    }
                }
                Student temp = students[minIndex];
                students[minIndex] = students[i];
                students[i] = temp;
            }
        }

        // Calculate average age
        public double calculateAverageAge() {
            if (studentCount == 0) {
                return 0;
            }
            int totalAge = 0;
            for (int i = 0; i < studentCount; i++) {
                totalAge += students[i].getAge();
            }
            return (double) totalAge / studentCount;
        }
    }

