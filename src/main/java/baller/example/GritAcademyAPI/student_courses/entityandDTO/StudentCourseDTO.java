package baller.example.GritAcademyAPI.student_courses.entityandDTO;


import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StudentCourseDTO {
    private long id;
    private long studentId;
    private long courseId;


    /** Returns a StudentCourseDTO(long id, long studentid, long courseid)
     *
     * @param studentCourse
     * @return studentCourseAsDTO
     */
    public static StudentCourseDTO mapToDTO(StudentCourse studentCourse) {
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setId(studentCourse.getId());
        dto.setStudentId(studentCourse.getStudents().getId());
        dto.setCourseId(studentCourse.getCourses().getId());


        return dto;

    }

    /**Maps a studentcourse to its corresponding courseId
     *
     * @param studentCourse
     * @return courseIdOfStudentCourseEntity
     */
    public static Long getCourseId(StudentCourse studentCourse){
        return studentCourse.getCourses().getId();
    }

    /**Maps a studentcourse to its corresponding studentId
     *
     * @param studentCourse
     * @return studentIdOfCourseEntity
     */
    public static Long getStudentId(StudentCourse studentCourse){
        return studentCourse.getStudents().getId();
    }
}
