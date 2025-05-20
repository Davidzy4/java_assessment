package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;
    }

    public boolean isSubscribed(String studentId) {
        return students.containsKey(studentId);
    }

    public void showSummary() {

        // each student, show student's details (id, email, name)
        // on top of that, shows the course(s) that each student is taking

        System.out.println("Existing students:");
        for (Student student : students.values()) {
            System.out.println(student); // already prints ID, name, email via Person's toString()

            System.out.println("Courses enrolled:");
            for (Course course : student.getCourses()) {
                System.out.println("- " + course.getCode() + ": " + course.getName());
            }

            System.out.println("Approved courses:");
            for (Course course : student.getApprovedCourses()) {
                System.out.println("✓ " + course.getCode() + ": " + course.getName());
            }

            double avg = student.getAverage();
            if (avg > 0) {
                System.out.printf("Average Grade: %.2f%n", avg);
            }

            int credits = student.getTotalCredits();
            System.out.println("Total Credits Earned: " + credits);

            System.out.println("--------");
        }
    }

    public boolean enrollToCourse(String studentId, Course course) {
        boolean status = false;

        if (students.containsKey(studentId))
        {
            status = students.get(studentId).enrollToCourse(course);
        }

        return status;
    }

}
