package com.artur.engineer.payload.course;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author Artur Pilch <artur.pilch12@gmail.com>
 */
public class CourseRemoveStudents {

    @NotBlank
    private List<Long> studentsIds;

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Long> studentsIds) {
        this.studentsIds = studentsIds;
    }
}
