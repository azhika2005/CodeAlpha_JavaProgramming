import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradesManager {

    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\n--- Student Grades Manager ---");
            System.out.println("1. Add student and grade");
            System.out.println("2. Show all student grades");
            System.out.println("3. Show summary (average, highest, lowest)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    showSummary();
                    break;
                case 4:
                    keepRunning = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        double grade;
        while (true) {
            System.out.print("Enter " + name + "'s grade (0-100): ");
            grade = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (grade >= 0 && grade <= 100) break;
            System.out.println("Invalid grade. Please enter a value between 0 and 100.");
        }

        students.add(new Student(name, grade));
        System.out.println("Student added successfully.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        System.out.println("\n--- Student Grades ---");
        for (Student s : students) {
            System.out.println("Name: " + s.name + " | Grade: " + s.grade);
        }
    }

    private static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("No student data to summarize.");
            return;
        }

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) highest = s.grade;
            if (s.grade < lowest) lowest = s.grade;
        }

        double average = total / students.size();

        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Students: " + students.size());
        System.out.printf("Average Grade: %.2f%n", average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
    }
}
