package com.sms;
import java.util.*;

public class StudentManager {
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }
    public boolean exists(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) return true;
        }
        return false;
    }
    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    public Student searchById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public boolean deleteById(int id) {
        Student toDelete = searchById(id);
        if (toDelete != null) {
            studentList.remove(toDelete);
            return true;
        }
        return false;
    }

    public List<Student> getAllStudents() {
        return studentList;
    }

    public void setAllStudents(List<Student> students) {
        this.studentList = students;
    }
}
