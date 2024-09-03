package baller.example.GritAcademyAPI.student_courses.repository;

import baller.example.GritAcademyAPI.student_courses.entityandDTO.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    /**Returns all StudentCourse-entities from the student_courses-table
     * with matching courses_ids
     * @param courses_id
     * @return studentCoursesByCourses_id
     */
    List<StudentCourse> searchStudentCourseByCourses_Id(long courses_id);
    /**Returns all StudentCourse-entities from the student_courses-table
     * with matching students_ids
     * @param students_id
     * @return studentCoursesByStudents_id
     */
    List<StudentCourse> findStudentCourseByStudents_Id(long students_id);
    /**Returns all StudentCourse-entities from the student_courses-table
     * with matching courses_ids to the set of courseids provided
     * @param courseids
     * @return studentCoursesByCourses_idsInSet
     */
    List<StudentCourse> findStudentCoursesByCourses_IdIn(Set<Long> courseids);

    /**Returns  StudentCourse from the student_courses-table
     * with matching courses_id and student_id to the set of longs provided
     *
     * @param studentId
     * @param courseid
     * @return studentCourseByStudents_idAndCourse_id
     */
    List<StudentCourse> findStudentCoursesByStudents_IdAndCourses_Id(long studentId,long courseid);

}
