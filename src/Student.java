// File: src/Student.java
public class Student {
    private int id;
    private String name;
    private float grade;
    private int attendance;

    // Constructor with all fields
    public Student(int id, String name, float grade, int attendance) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
    }

    // Constructor without ID (for insert operations)
    public Student(String name, float grade, int attendance) {
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
