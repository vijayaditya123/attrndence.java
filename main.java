import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private boolean isPresent;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.isPresent = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}

class AttendanceManagementSystem {
    private List<Student> students;
    private Map<String, List<Student>> attendanceRecords;

    public AttendanceManagementSystem() {
        students = new ArrayList<>();
        attendanceRecords = new HashMap<>();
    }

    public void addStudent(int id, String name) {
        students.add(new Student(id, name));
    }

    public void markAttendance(String date) {
        Scanner scanner = new Scanner(System.in);
        List<Student> attendanceForTheDay = new ArrayList<>();
        for (Student student : students) {
            System.out.print("Is " + student.getName() + " present? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                student.setPresent(true);
            } else {
                student.setPresent(false);
            }
            attendanceForTheDay.add(student);
        }
        attendanceRecords.put(date, new ArrayList<>(attendanceForTheDay));
        System.out.println("Attendance marked for " + date);
    }

    public void viewAttendance(String date) {
        List<Student> attendanceForTheDay = attendanceRecords.get(date);
        if (attendanceForTheDay != null) {
            System.out.println("Attendance for " + date + ":");
            for (Student student : attendanceForTheDay) {
                System.out.println(student.getName() + " - " + (student.isPresent() ? "Present" : "Absent"));
            }
        } else {
            System.out.println("No attendance records found for " + date);
        }
    }

    public void listStudents() {
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        AttendanceManagementSystem attendanceSystem = new AttendanceManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Adding some students to the system
        attendanceSystem.addStudent(1, "Alice");
        attendanceSystem.addStudent(2, "Bob");
        attendanceSystem.addStudent(3, "Charlie");

        do {
            System.out.println("\nAttendance Management System:");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. List Students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    attendanceSystem.markAttendance(date);
                    break;
                case 2:
                    System.out.print("Enter the date to view attendance (YYYY-MM-DD): ");
                    String viewDate = scanner.nextLine();
                    attendanceSystem.viewAttendance(viewDate);
                    break;
                case 3:
                    attendanceSystem.listStudents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}
