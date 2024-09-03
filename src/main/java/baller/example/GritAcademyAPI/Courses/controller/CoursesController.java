package baller.example.GritAcademyAPI.Courses.controller;

import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Courses.repository.CoursesRepository;
import baller.example.GritAcademyAPI.Courses.service.CoursesService;
import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import baller.example.GritAcademyAPI.Students.repository.StudentsRepository;
import baller.example.GritAcademyAPI.Students.service.StudentsService;
import baller.example.GritAcademyAPI.student_courses.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController

public class CoursesController {



    private CoursesService coursesService;
    private StudentCourseService studentCourseService;

    /** A restcontroller that handles the Get-mappings under /courses
     *
     * @param coursesServiceIn
     * @param studentCourseServiceIn
     */
    @Autowired
    public CoursesController(CoursesService coursesServiceIn, StudentCourseService studentCourseServiceIn){
       coursesService = coursesServiceIn;
       studentCourseService = studentCourseServiceIn;




    }

    /** Maps to /courses(?{name,namecontains, coursecontains}=<value>
     *
     * @param name
     * @param nameFragment
     * @param descriptionFragment
     * @return filteredCoursesDTOList with request-parameters, whole courses-table if no parameters specified
     */
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(@RequestParam(name = "name",required = false)String name,@RequestParam(name = "namecontains",required = false)String nameFragment,@RequestParam(name = "descriptioncontains",required = false)String descriptionFragment){

        if(name != null ){
            ResponseEntity<List<CourseDTO>> responseEntity = new ResponseEntity<>(coursesService.getCourseByName(name),HttpStatusCode.valueOf(200));
            return responseEntity;

        } else if(nameFragment!=null && descriptionFragment != null){
            ResponseEntity<List<CourseDTO>> responseEntity = new ResponseEntity<>(coursesService.getCoursesByNameAndDescriptionFragment(nameFragment,descriptionFragment),HttpStatusCode.valueOf(200));
            return responseEntity;
        }
        else if(descriptionFragment != null){
            ResponseEntity<List<CourseDTO>> responseEntity = new ResponseEntity<>(coursesService.getCoursesByDescriptionFragment(descriptionFragment),HttpStatusCode.valueOf(200));
            return responseEntity;


        }
        else if(nameFragment!=null){
            ResponseEntity<List<CourseDTO>> responseEntity = new ResponseEntity<>(coursesService.getCoursesByNameFragment(nameFragment),HttpStatusCode.valueOf(200));
            return responseEntity;


        }

            /*If all request-parameters are null we just get the entire table*/
            ResponseEntity<List<CourseDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllCourses(),HttpStatusCode.valueOf(200));
            return responseEntity;




    }

    /** Gets the course entity with the specified id ; Maps to /course/{id}
     *
     * @param id
     * @return empty list if course with id doesn´t exist, the specified entity otherwise
     */
    @GetMapping("/courses/{id}")
    public ResponseEntity<Optional<CourseDTO>> getCourseById(@PathVariable(name = "id") long id){

        ResponseEntity<Optional<CourseDTO>> responseEntity = new ResponseEntity(coursesService.getCourseById(id), HttpStatusCode.valueOf(200));
        return responseEntity;
    }


    /**Maps to /courses/students(?{name,namecontains,descriptioncontains}=<value>
     *  -> Returns the students of the course matching the filters, otherwise all courses with associated students
     * @param name
     * @param nameFragment
     * @param descriptionFragment
     * @return
     */
    @GetMapping("/courses/students")
    public ResponseEntity<List<StudentDTO>> getStudentsInClasses(@RequestParam(name = "name",required = false)String name,@RequestParam(name = "namecontains",required = false)String nameFragment,@RequestParam(name = "descriptioncontains",required = false)String descriptionFragment) {

        /*name and namefragment can´t be run at the same time -> If a name is set only entities owning that name will be shown - Regardless of description and namecontent*/
        if (name != null) {
            /*Only works when one row as result, thereby change to id blater*/
            Set<CourseDTO> courseResult = coursesService.getCourseByName(name).stream().collect(Collectors.toSet());
            ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllStudentsByCourseDTO(courseResult),HttpStatusCode.valueOf(200));
            return responseEntity;


        }
        else if (nameFragment != null && descriptionFragment != null) {
                /*We can find student by a set of courses*/
                Set<Long> courseResults = coursesService.getCoursesEntityByNameAndDescriptionFragment(nameFragment, descriptionFragment).stream().map(Courses::getId).collect(Collectors.toSet());
                /*We use this course-set to find their associated students*/
                ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllStudentsByCourseIds(courseResults),HttpStatusCode.valueOf(200));
                return responseEntity;


            }
        else if (nameFragment != null ) {
            /*We can find student by a set of courses*/
            Set<Long> courseResults = coursesService.getCoursesByNameFragment(nameFragment).stream().map(CourseDTO::getId).collect(Collectors.toSet());
            /*We use this course-set to find their associated students*/
            ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllStudentsByCourseIds(courseResults),HttpStatusCode.valueOf(200));
            return responseEntity;


        }
            else if(descriptionFragment != null){
            Set<Long> courseResults = coursesService.getCoursesByDescriptionFragment(descriptionFragment).stream().map(CourseDTO::getId).collect(Collectors.toSet());

            ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllStudentsByCourseIds(courseResults),HttpStatusCode.valueOf(200));
            return responseEntity;

        }




            ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getAllCoursesWithStudents(), HttpStatusCode.valueOf(200));
            return responseEntity;

    }

    /** Maps to /courses/{id}/students
     *
     * @param id
     * @return list of students associated with the course
     */
    @GetMapping(value = "courses/{id}/students")
    public ResponseEntity<List<StudentDTO>> studentsByCourse(@PathVariable(name = "id") long id){


            ResponseEntity<List<StudentDTO>> responseEntity = new ResponseEntity<>(coursesService.getStudentsByCourseId(id), HttpStatusCode.valueOf(200));
            return responseEntity;
        }











}