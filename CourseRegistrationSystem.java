import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    int enrolled;
    

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        
    }

    public boolean register() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean drop() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Code: " + code + ", Title: " + title + ", Description: " + description +
               ", Capacity: " + capacity + ", Enrolled: " + enrolled  ;
    }
}

class Student {
    String id;
    String name;
    ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registerCourse(Course course) {
        if (course.register()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course) && course.drop()) {
            registeredCourses.remove(course);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Registered Courses: " + registeredCourses;
    }
}

public class CourseRegistrationSystem {
    static HashMap<String, Course> courseDatabase = new HashMap<>();
    static HashMap<String, Student> studentDatabase = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();

        while (true) {
            System.out.println("1. Display Courses");
            System.out.println("2. Register Student for Course");
            System.out.println("3. Drop Student from Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerStudentForCourse();
                    break;
                case 3:
                    dropStudentFromCourse();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void initializeData() {
        courseDatabase.put("CS001", new Course("CS001", "Intro to Computer Science", "Basics of CS", 30));
        courseDatabase.put("cs002", new Course("cs002", "C Programming", "Introduction to C Programming", 25));
        courseDatabase.put("CS003", new Course("CS003", "Data Structure Alogithm ", "Basics of DSA",45 ));
        courseDatabase.put("CS004", new Course("CS004", "JAVA Programming ", "familiar To JAVA",25));
        courseDatabase.put("CS005", new Course("CS005", "Python Programming ", "basics of python",45));

        studentDatabase.put("S001", new Student("S001", "Avinash"));
        studentDatabase.put("S002", new Student("S002", "Bob"));
        studentDatabase.put("S003", new Student("S003", "Jack"));
        studentDatabase.put("S004", new Student("S004", "Somya"));
        studentDatabase.put("S005", new Student("S005", "Rajat"));
        studentDatabase.put("S006", new Student("S006", "Shikhar"));
        studentDatabase.put("S007", new Student("S007", "Shubh"));
        studentDatabase.put("S008", new Student("S008", "Shristi"));
        studentDatabase.put("S009", new Student("S009", "khusi"));
        studentDatabase.put("S010", new Student("S010", ""));




    }

    public static void displayCourses() {
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    public static void registerStudentForCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.registerCourse(course)) {
            System.out.println("Course registered successfully.");
        } else {
            System.out.println("Failed to register for the course. It might be full.");
        }
    }

    public static void dropStudentFromCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = studentDatabase.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.dropCourse(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Failed to drop the course. It might not be registered.");
        }
    }
}
