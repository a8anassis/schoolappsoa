package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {

    private TeacherValidator() {}

    public static Map<String, String> validate(TeacherInsertDTO dto) {
        Map<String, String> errors = new HashMap<>();
        if (dto.getFirstname().length() < 3 || dto.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (dto.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (dto.getLastname().length() < 3 || dto.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (dto.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }

        // if (userService.isUsernameTaken(dto.getUsername()) {
        // }

        return errors;
    }
}
