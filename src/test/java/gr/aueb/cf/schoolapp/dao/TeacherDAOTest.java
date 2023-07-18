package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {

    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    void setUp() throws TeacherDAOException {
        createDummyTeachers();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Bob");
        teacher.setLastname("Marley");
        teacherDAO.insert(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname("Marl");
        assertEquals(1, teachers.size());
    }

    public static void createDummyTeachers() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Athanassios");
        teacher.setLastname("Androutsos");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Kefala");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Makis");
        teacher.setLastname("kapetis");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("John");
        teacher.setLastname("Papa");
        teacherDAO.insert(teacher);
    }

}