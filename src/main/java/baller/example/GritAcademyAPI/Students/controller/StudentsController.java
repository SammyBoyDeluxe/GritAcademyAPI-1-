package baller.example.GritAcademyAPI.Students.controller;

import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.repository.StudentsRepository;
import baller.example.GritAcademyAPI.Students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController

public class StudentsController {
    private final StudentsRepository studentsRepository;
    private StudentsService studentsService;

    /**Handeles all functions relating to the students-endpoint
     *
     * @param studentsServiceIn
     * @param studentsRepository
     */
    @Autowired
public StudentsController(StudentsService studentsServiceIn, StudentsRepository studentsRepository){
    studentsService = studentsServiceIn;
    this.studentsRepository = studentsRepository;
}

    /**Maps to /students(?{fname,lname,town}=<value>)
     *
     * @param fname
     * @param lname
     * @param town
     * @return all students with null parameters, filteredstudentsTableOtherwise
     */
    @RequestMapping("/students")
    public ResponseEntity<List<StudentDTO>>getStudentTable(@RequestParam(required = false, name = "fname") String fname, @RequestParam(name = "lname",required = false) String lname, @RequestParam(name = "town", required = false)String town){
    /*This method will return the entire student-table if no attributes are provided and otherwise it will filter it
    using the request-parameters
     */
    ResponseEntity<List<StudentDTO>> studentResponseEntity = new ResponseEntity<>(studentsService.getStudentsByAttributes(fname,lname,town), HttpStatusCode.valueOf(200));
    return studentResponseEntity;


    }

    /**Maps to /students/{id}
     *
     * @param id
     * @return studentByIdAsResponse
     */
    @GetMapping("students/{id}")
    public ResponseEntity<Optional<StudentDTO>> getStudentByIdWithoutCourses(@PathVariable(name="id")long id){

    ResponseEntity<Optional<StudentDTO>> responseEntity = new ResponseEntity<>(studentsService.getStudentById(id),HttpStatusCode.valueOf(200));
    return responseEntity;
    }

    /** Maps to/students/{id}/courses ->Returns joined table of students and courses by studentId
     *
     * @param id
     * @return
     */
    @GetMapping("students/{id}/courses")
    public ResponseEntity<Optional<StudentDTO>> getStudentByIdWithCourses(@PathVariable(name="id")long id){

        ResponseEntity<Optional<StudentDTO>> responseEntity = new ResponseEntity<>(studentsService.getStudentByIdWithCourses(id),HttpStatusCode.valueOf(200));
        return responseEntity;
    }

    /**Maps to /students/courses(?{fname,lname,town}=<value>)
     * Gets studentDTO list with courses included
     * @param fname
     * @param lname
     * @param town
     * @return filteredStudentTableWithAssociatedCourses
     */
    @GetMapping("students/courses")
    public ResponseEntity<List<StudentDTO>>getFilteredStudentTableWithCourses(@RequestParam(required = false, name = "fname") String fname, @RequestParam(name = "lname",required = false) String lname, @RequestParam(name = "town", required = false)String town){
    /*This method will return the entire student-table if no attributes are provided and otherwise it will filter it
    using the request-parameters
     */
        ResponseEntity<List<StudentDTO>> studentResponseEntity = new ResponseEntity<>(studentsService.getStudentsByAttributesWithCourses(fname,lname,town), HttpStatusCode.valueOf(200));
        return studentResponseEntity;
    }





}
