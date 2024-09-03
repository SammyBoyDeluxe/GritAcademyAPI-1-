package baller.example.GritAcademyAPI.Students.entityAndDTO;

import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

public class StudentDTO{
    private long id;
    private String fname;
    private String lname;
    private String town;
    private Set<CourseDTO> courses;

    /**Gets all student entries and presents these and all their attributes
     * together with their associated courses via the students_courses-table
     *
     * @param students
     * @return studentDTOWithCourses
     */
    public static StudentDTO mapToDTOWithCourses(Students students) {
        StudentDTO dto = new StudentDTO();
        dto.setId(students.getId());
        dto.setFname(students.getFname());
        dto.setLname(students.getLname());
        dto.setTown(students.getTown());
        dto.setCourses(( students.getCourses().stream().map(CourseDTO::mapToDTO).collect(Collectors.toSet())));
        return dto;

    }

    /**Gets student-row without courses-entry -> Called on
     * /students to present the base student-table, not the associations
     *
     *
     * @param students
     * @return
     */
    public static StudentDTO mapToDTO(Students students) {
        StudentDTO dto = new StudentDTO();
        dto.setId(students.getId());
        dto.setFname(students.getFname());
        dto.setLname(students.getLname());
        dto.setTown(students.getTown());
        return dto;

    }

}
