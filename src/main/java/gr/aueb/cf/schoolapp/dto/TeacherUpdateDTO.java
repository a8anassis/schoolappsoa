package gr.aueb.cf.schoolapp.dto;

public class TeacherUpdateDTO extends Base {
    private String firstname;
    private String lastname;

    public TeacherUpdateDTO() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
