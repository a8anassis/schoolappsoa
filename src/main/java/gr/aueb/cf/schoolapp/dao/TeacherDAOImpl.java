package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {
    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);) {
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();

            if (n == 1) {
                JOptionPane.showMessageDialog(null,  n + " row affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
                return teacher;
            }
            else return null;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher" + teacher + " insertion");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();
            if (n == 1) {
                JOptionPane.showMessageDialog(null,  n + " row affected", "Update", JOptionPane.INFORMATION_MESSAGE);
                return teacher;
            } else return null;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher" + teacher + " insertion");
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
                e.printStackTrace();
            throw new TeacherDAOException("SQL Error in Teacher with id: " + id + " deletion");
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ) {
            ResultSet rs;
            ps.setString(1, Main.getTeachersSearchForm().getLastname() + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
                teachers.add(teacher);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        return teachers;
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs;
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return teacher;
    }
}
