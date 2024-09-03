package baller.example.GritAcademyAPI.student_courses.controller;

import baller.example.GritAcademyAPI.student_courses.entityandDTO.StudentCourseDTO;
import baller.example.GritAcademyAPI.student_courses.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student_courses")
public class StudentCoursesController {
    private StudentCourseService studentCourseService;

    /** Handles the requests for /students_courses
     *
     * @param studentCourseServiceIn
     */
    @Autowired
    public StudentCoursesController(StudentCourseService studentCourseServiceIn){
        studentCourseService = studentCourseServiceIn;
    }

    /** Maps to /students_courses(?{studentid,courseid}=<value>) Gets the filtered-table on studentid, courseid or both.
     * If both are null we get all students_courses as DTOs
     * @param studentId
     * @param courseId
     * @return filteredListOnAvailableAttributes if request-parameters are available, all students_courses if both are null
     */
    @GetMapping("")
    public ResponseEntity<List<StudentCourseDTO>> getStudentCourses(@RequestParam(name = "studentid",required = false) String studentId, @RequestParam(name = "courseid",required = false)String courseId){
        ResponseEntity<List<StudentCourseDTO>> responseEntity = new ResponseEntity<>(studentCourseService.getStudentCourses(studentId,courseId), HttpStatusCode.valueOf(200));
        return responseEntity;

    }

    /** Maps to /students_courses/{id} ; Gets the student_courses entity owning the specific id
     *
     * @param id
     * @return studentCourseAsDTO
     */
    @GetMapping("/{id}")
    public  ResponseEntity<List<StudentCourseDTO>> getStudentCourseById(@PathVariable(name = "id") long id){
        ResponseEntity<List<StudentCourseDTO>> responseEntity = new ResponseEntity<>(studentCourseService.getStudentCoursesById(id), HttpStatusCode.valueOf(200));
        return responseEntity;
    }

}
