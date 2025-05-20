package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
            }
        }
        while (option != 7);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        courseService.enrollStudent(courseId, student);
        boolean status = studentService.enrollToCourse(studentId, course);
        if (status)
            System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);
        else
            System.out.println("Cannot enrol Student with ID: " + studentId + " already has been enrolled");
    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }

    private static void gradeStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (student.getCourses().isEmpty()) {
            System.out.println("This student is not enrolled in any courses.");
            return;
        }

        System.out.println("Courses the student is enrolled in:");
        for (Course course : student.getCourses()) {
            System.out.println(course.getCode() + ": " + course.getName());
        }

        System.out.println("Enter course code to grade: ");
        String courseCode = scanner.next();
        if (!student.isAttendingCourse(courseCode)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }

        System.out.println("Enter the score (0-9): ");
        double score = scanner.nextDouble();
        if (score < 0 || score > 9) {
            System.out.println("Invalid score. Must be between 0 and 9.");
            return;
        }

        student.setGrade(courseCode, score);
        System.out.println("Grade set for course " + courseCode + ". Score: " + score);
    }

    private static void findStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner) {
        try {
            Student student = PrinterHelper.createStudentMenu(scanner);
            studentService.subscribeStudent(student);
            System.out.println("Student registered successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM/DD/YYYY.");
        }
    }
}
