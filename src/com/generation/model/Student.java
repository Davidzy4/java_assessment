package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();
    private final Map<String, Course> approvedCourses = new HashMap<>();
    private final Map<String, Double> grades = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate)
    {
        super(id, name, email, birthDate);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public boolean enrollToCourse(Course course) {
        if (courses.contains(course)) {
            System.out.println("Already enrolled to this course.");
            return false;
        }
        this.courses.add(course);
        return true;
    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public boolean isCourseApproved(String courseCode) {
        return approvedCourses.containsKey(courseCode);
    }

    public boolean isAttendingCourse(String courseCode) {
        for (Course c : courses) {
            if (c.getCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void setGrade(String courseCode, double score) {
        grades.put(courseCode, score);
        if (score >= 4.5 && isAttendingCourse(courseCode)) {
            for (Course c : courses) {
                if (c.getCode().equals(courseCode)) {
                    registerApprovedCourse(c);
                    break;
                }
            }
        }
    }

    public Double getGrade(String courseCode) {
        return grades.get(courseCode);
    }

    public List<Course> findPassedCourses(Course dummyInputToMatchSignature) {
        List<Course> passed = new ArrayList<>();
        for (String code : grades.keySet()) {
            if (grades.get(code) >= 4.5) {
                passed.add(approvedCourses.get(code));
            }
        }
        return passed;
    }

    public int getTotalCredits() {
        int total = 0;
        for (Course course : getApprovedCourses()) {
            total += course.getCredits(); // Assuming 9 for passed courses
        }
        return total;
    }

    @Override
    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double total = 0;
        for (double score : grades.values()) {
            total += score;
        }
        return total / grades.size();
    }

    @Override
    public List<Course> getApprovedCourses() {
        return new ArrayList<>(approvedCourses.values());
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}