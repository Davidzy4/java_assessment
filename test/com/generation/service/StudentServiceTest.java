import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    public void setup() {
        studentService = new StudentService();
        courseService = new CourseService(); // create this to get real courses
    }

    @Test
    public void testSubscribeAndFindStudent() {
        Student student = new Student("S1", "Alice", "alice@hotmail.com", new Date());
        studentService.subscribeStudent(student);

        Student retrieved = studentService.findStudent("S1");
        assertNotNull(retrieved);
        assertEquals("Alice", retrieved.getName());
        assertEquals("alice@hotmail.com", retrieved.getEmail());
    }

    @Test
    public void testEnrollToCourse_UsingRealCourseCode() {
        Student student = new Student("S2", "Bob", "bob@example.com", new Date());
        studentService.subscribeStudent(student);

        Course course = courseService.getCourse("INTRO-CS-1"); // using actual course code
        assertNotNull(course, "Course should exist");

        boolean enrolled = studentService.enrollToCourse("S2", course);
        assertTrue(enrolled);
        assertTrue(student.getCourses().contains(course));
    }
}