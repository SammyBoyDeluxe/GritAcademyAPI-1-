package baller.example.GritAcademyAPI.student_courses.service;

import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import baller.example.GritAcademyAPI.student_courses.entityandDTO.StudentCourse;
import baller.example.GritAcademyAPI.student_courses.entityandDTO.StudentCourseDTO;
import baller.example.GritAcademyAPI.student_courses.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    private StudentCourseRepository studentCourseRepository;

    /**Handles business-logic relating to the studentcourses-entities
     *
     * @param studentCourseRepositoryIn
     */
    @Autowired
    public StudentCourseService(StudentCourseRepository studentCourseRepositoryIn){
        studentCourseRepository = studentCourseRepositoryIn;
    }

    /** Returns matching studentcourse with id
     *
     * @param id
     * @return
     */
    public List<StudentCourseDTO> getStudentCoursesById(long id){

        return studentCourseRepository.findById(id).stream().map(StudentCourseDTO::mapToDTO).collect(Collectors.toList());


    }

    /** Gets studentIds associated with a certain set of courseids
     *
     * @param courseIds
     * @return setOfStudentIds
     */
    public Set<Long> getStudentIdsByCourseId(Set<Long> courseIds){
       return studentCourseRepository.findStudentCoursesByCourses_IdIn(courseIds).stream().map(StudentCourseDTO::getStudentId).collect(Collectors.toSet());
    }

    /**Gets the associated table-entries for students filtering on studentid(singular),courseid(singular),both or
     * if both are null the entire joined students and courses table is returned
     *
     * @param studentId
     * @param courseId
     * @return listOfStudentsWithCoursesByFilter
     */
        public List<StudentCourseDTO> getStudentCourses(String studentId, String courseId ){

         if(studentId != null && courseId !=null){

            return studentCourseRepository.findStudentCoursesByStudents_IdAndCourses_Id(Long.parseLong(studentId), Long.parseLong(courseId)).stream().map(StudentCourseDTO::mapToDTO).collect(Collectors.toList());
        }
            else if(studentId != null){

                return studentCourseRepository.findStudentCourseByStudents_Id(Long.parseLong(studentId)).stream().map(StudentCourseDTO::mapToDTO).collect(Collectors.toList());
            }
        else if(courseId != null){
            return studentCourseRepository.searchStudentCourseByCourses_Id(Long.parseLong(courseId)).stream().map(StudentCourseDTO::mapToDTO).collect(Collectors.toList());

        }

        return studentCourseRepository.findAll().stream().map(StudentCourseDTO::mapToDTO).collect(Collectors.toList());




    }
}
