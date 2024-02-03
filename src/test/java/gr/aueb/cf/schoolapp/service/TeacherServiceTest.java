package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {
    private static ITeacherDAO teacherDAO;
    private static ITeacherService teacherService;


    @BeforeAll
    public static void setupClass() {
        teacherDAO = new TeacherDAOImpl();
        teacherService = new TeacherServiceImpl(teacherDAO);
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyTeachers();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetTeacher() throws TeacherDAOException {
        TeacherInsertDTO dto = new TeacherInsertDTO("Olga", "Krug");
        teacherService.insertTeacher(dto);

        List<Teacher> teachers = teacherService.getTeachersByLastname("Krug");
        assertEquals(1, teachers.size());
        //assertEquals(dto.getFirstname(), teachers.get(0).getFirstname());
    }

    @Test
    public void updateTeacher() throws TeacherDAOException, TeacherNotFoundException {
        TeacherUpdateDTO dto = new TeacherUpdateDTO(1, "Andreas", "Androutsos2");
        teacherService.updateTeacher(dto);

        List<Teacher> teachers = teacherService.getTeachersByLastname("Androutsos2");
        assertEquals(dto.getFirstname(), teachers.get(0).getFirstname());
        assertEquals(dto.getLastname(), teachers.get(0).getLastname());
    }

    @Test
    public void updateInvalidTeacher() {
        TeacherUpdateDTO dto = new TeacherUpdateDTO(10, "Andreas", "Androutsos2");

        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.updateTeacher(dto);
        });
    }

    @Test
    public void deleteTeacher() throws TeacherNotFoundException, TeacherDAOException {
        int id = 1;
        teacherService.deleteTeacher(id);

        List<Teacher> teachers = teacherService.getTeachersByLastname("Androu");
        assertEquals(0, teachers.size());
    }

    @Test
    public void deleteInvalidTeacher() {
        int id = 20;
        assertThrows(TeacherNotFoundException.class, () -> teacherService.deleteTeacher(id));
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
        teacher.setLastname("Kapetidis");
        teacherDAO.insert(teacher);
    }
}