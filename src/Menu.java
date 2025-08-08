import java.util.*;

public class Menu {
    Scanner sc = new Scanner(System.in);
    StudentDAO dao = new StudentDAO();

    public void display() {
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

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
                    System.exit(0);
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
        sc.nextLine();
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
}
