import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void addStudent(Student s) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO students(name, grade, attendance) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setFloat(2, s.getGrade());
            ps.setInt(3, s.getAttendance());
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"),
                        rs.getFloat("grade"), rs.getInt("attendance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(int id, String name, float grade, int attendance) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE students SET name=?, grade=?, attendance=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, grade);
            ps.setInt(3, attendance);
            ps.setInt(4, id);
            ps.executeUpdate();
            System.out.println("Student updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
