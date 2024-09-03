package baller.example.GritAcademyAPI.Courses.entityandDTO;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class CourseDTO {
    private long id;
    private String name;
    private String description;

    /**Maps a course to the DTO using; CourseDTO(long id, String name,String description )
     *
     * @param courses
     * @return courseAsDTO
     */
    public static CourseDTO mapToDTO(Courses courses) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.id = courses.id;
        courseDTO.name = courses.name;
        courseDTO.description = courses.description;
        return courseDTO;
    }

    /**Maps a courseDTO to an entity ; Courses(long id, String name, String description)
     *
     * @param courseDTO
     * @return courseDTOasCourses
     */
    public static Courses mapToEntity(CourseDTO courseDTO){
        Courses courses = new Courses();

        courses.id = courseDTO.id;

        courses.name = courseDTO.name;

        courses.description = courseDTO.getDescription();

        return courses;
    }
}
