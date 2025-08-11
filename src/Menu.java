// File: src/Menu.java

import java.util.List;
import java.util.Scanner;
public class Menu {
    Scanner sc = new Scanner(System.in);
    
    StudentDAO dao = new StudentDAO();
    
    
    public void display() {
        System.out.println("Enter user:student/admin:");
    String user=sc.nextLine();
        if(user.equals("admin")){
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search student by name");
            System.out.println("6. Filter students by grade or attendance");
            System.out.println("7. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    filterStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }}
        else{
             while (true) {
            System.out.println("1. View Students");
            System.out.println("2. Search student by name");
            System.out.println("3. Filter students by grade or attendance");
            System.out.println("4. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                
                case 1:
                    view();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    filterStudents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
           
        }
    }

    private void add() {
        System.out.print("Enter Name: ");
        sc.nextLine(); // consume leftover newline
        String name = sc.nextLine();
        System.out.print("Enter Grade: ");
        float grade = sc.nextFloat();
        System.out.print("Enter Attendance: ");
        int att = sc.nextInt();

        dao.addStudent(new Student(name, grade, att));
    }

    private void view() {
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getGrade() + " - " + s.getAttendance());
        }
    }

    private void update() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        System.out.print("New Name: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();
        System.out.print("New Grade: ");
        float grade = sc.nextFloat();
        System.out.print("New Attendance: ");
        int att = sc.nextInt();

        dao.updateStudent(id, name, grade, att);
    }

    private void delete() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        dao.deleteStudent(id);
    }

    private void searchByName() {
        sc.nextLine(); 
        System.out.print("Enter name keyword to search: ");
        String keyword = sc.nextLine();

        List<Student> results = dao.searchStudentsByName(keyword);
        if (results.isEmpty()) {
            System.out.println("No students found with name containing: " + keyword);
        } else {
            System.out.println("Search results:");
            for (Student s : results) {
                System.out.println(s.getId() + " | " + s.getName() + " | " + s.getGrade() + " | " + s.getAttendance());
            }
        }
    }

    
    private void filterStudents() {
        System.out.println("1. Filter by Grade");
        System.out.println("2. Filter by Attendance");
        System.out.print("Choose option: ");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.print("Enter minimum grade: ");
                float minGrade = sc.nextFloat();
                List<Student> gradeFiltered = dao.filterStudentsByGrade(minGrade);
                if (gradeFiltered.isEmpty()) {
                    System.out.println("No students with grade >= " + minGrade);
                } else {
                    for (Student s : gradeFiltered) {
                        System.out.println(s.getId() + " | " + s.getName() + " | " + s.getGrade() + " | " + s.getAttendance());
                    }
                }
                break;

            case 2:
                System.out.print("Enter minimum attendance: ");
                int minAtt = sc.nextInt();
                List<Student> attFiltered = dao.filterStudentsByAttendance(minAtt);
                if (attFiltered.isEmpty()) {
                    System.out.println("No students with attendance >= " + minAtt);
                } else {
                    for (Student s : attFiltered) {
                        System.out.println(s.getId() + " | " + s.getName() + " | " + s.getGrade() + " | " + s.getAttendance());
                    }
                }
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}
