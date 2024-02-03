package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void gettersSetters() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setFirstname("Thanassis");
        teacher.setLastname("Androutsos");

        assertEquals(1, teacher.getId());
        assertEquals("Thanassis", teacher.getFirstname());
        assertEquals("Androutsos", teacher.getLastname());
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(2, "Anna", "Kefala");
        assertEquals(2, teacher.getId());
        assertEquals("Anna", teacher.getFirstname());
        assertEquals("Kefala", teacher.getLastname());

        String expected = "2, Anna, Kefala";
        String actual = teacher.toString();
        assertEquals(expected, actual);
    }
}