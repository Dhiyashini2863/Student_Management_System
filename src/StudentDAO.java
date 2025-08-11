// File: src/StudentDAO.java

import java.sql.*;
import java.util.*;

public class StudentDAO {

    // Add student
    public void addStudent(Student student) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO students (name, grade, attendance) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setFloat(2, student.getGrade());
            stmt.setInt(3, student.getAttendance());
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("grade"),
                    rs.getInt("attendance")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update student
    public void updateStudent(int id, String name, float grade, int attendance) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE students SET name = ?, grade = ?, attendance = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setFloat(2, grade);
            stmt.setInt(3, attendance);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete student
    public void deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Search students by name
    public List<Student> searchStudentsByName(String keyword) {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("grade"),
                    rs.getInt("attendance")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<Student> filterStudentsByGrade(float minGrade) {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE grade >= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, minGrade);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("grade"),
                    rs.getInt("attendance")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<Student> filterStudentsByAttendance(int minAttendance) {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE attendance >= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, minAttendance);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("grade"),
                    rs.getInt("attendance")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
