package baller.example.GritAcademyAPI.Courses.service;

import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Courses.repository.CoursesRepository;
import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.repository.StudentsRepository;
import baller.example.GritAcademyAPI.Students.service.StudentsService;
import baller.example.GritAcademyAPI.student_courses.repository.StudentCourseRepository;
import baller.example.GritAcademyAPI.student_courses.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoursesService {

    private final StudentCourseRepository studentCourseRepository;
    private CoursesRepository coursesRepository;
    private StudentsRepository studentsRepository;
    private StudentsService studentsService;
    private StudentCourseService studentCourseService;

    /**Courses-Service do the necessary functions in the business-layer
     * and handles all course-related business logic
     * @param coursesRepositoryIn
     * @param studentsRepositoryIn
     * @param studentCourseRepository
     * @param studentCourseServiceIn
     * @param studentCourseRepositoryIn
     */
    @Autowired
    public CoursesService(CoursesRepository coursesRepositoryIn, StudentsRepository studentsRepositoryIn, StudentCourseRepository studentCourseRepository,StudentCourseService studentCourseServiceIn, StudentCourseRepository studentCourseRepositoryIn) {
        this.coursesRepository = coursesRepositoryIn;
        this.studentsRepository = studentsRepositoryIn;
        studentsService = new StudentsService(studentsRepositoryIn);
        this.studentCourseRepository = studentCourseRepository;
        studentCourseService = new StudentCourseService(studentCourseRepository);
    }

    /**Gets all courses in the courses-table
     *
     * @return allCoursesAsDTOs
     */
    public List<CourseDTO> getAllCourses(){
    return coursesRepository.findAll().stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());

    }

    /**Gets course by id (singular)
     *
     * @param id
     * @return courseAsDTOSet
     */
    public Set<CourseDTO>  getCourseById(long id){
    return coursesRepository.findById(id).stream().map(CourseDTO::mapToDTO).collect(Collectors.toSet());
    }


    public List<CourseDTO> findCourseByName(String name){
        return coursesRepository.findCoursesByName(name).stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());

    }

    /**Gets all students associated with a course id
     *
     * @param id
     * @return studentsAsDTOs
     */
    public List<StudentDTO> getStudentsByCourseId(long id){
        Set<CourseDTO> courseById = this.getCourseById(id);
        /*Invokes student-service since that is the owning side of the relationship*/
       return studentsService.getAllStudentsByCourseDTO(courseById);



    }

    /**Gets a list of the filtered courses table by partial matches against the description
     *
     * @param descriptionFragment
     * @return listOfFilteredByDescriptionContainingCoursesTable
     */
    public List<CourseDTO> getCoursesByDescriptionFragment(String descriptionFragment){
        return coursesRepository.findCoursesByDescriptionContainingIgnoreCase(descriptionFragment).stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());
    }

    /**Gets a list of the filtered courses table by partial matches against the name attribute
     *
     * @param nameFragment
     * @return
     */
    public List<CourseDTO> getCoursesByNameFragment(String nameFragment){
       return coursesRepository.findCoursesByNameContainingIgnoreCase(nameFragment).stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());

    }

    /** Gets courses by name and decription partial match, converts them to DTOs
     *
     * @param nameFragment
     * @param descriptionFragment
     * @return
     */
    public List<CourseDTO> getCoursesByNameAndDescriptionFragment(String nameFragment, String descriptionFragment ){

         return coursesRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(nameFragment,descriptionFragment).stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());


    }

    /**Returns courses by name and description partial match as Courses-objects
     *
     * @param nameFragment
     * @param descriptionFragment
     * @return
     */
    public Set<Courses> getCoursesEntityByNameAndDescriptionFragment(String nameFragment, String descriptionFragment ){

        return coursesRepository.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(nameFragment,descriptionFragment).stream().collect(Collectors.toSet());


    }

    /** Gets all courses with matching name-attribute
     *
     * @param name
     * @return
     */
    public List <CourseDTO> getCourseByName(String name) {
       return coursesRepository.findCoursesByName(name).stream().map(CourseDTO::mapToDTO).collect(Collectors.toList());

    }


    /**Since students are the owning entity-type we return a list of studentDTO with an
     * associated CourseDTO
     *
     *
     * @return List<>StudentDTO</>
     */
    public List<StudentDTO> getAllCoursesWithStudents(){
        return studentsService.getAllStudentsWithCourses();

    }

    public List<StudentDTO> getAllStudentsByCourse( Set<Courses> course){
        return studentsService.getAllStudentsInCourse(course);

    }

    /**Gets the associated student by courses represented as CourseDTOs
     *
     * @param courses
     * @return
     */
    public List<StudentDTO> getAllStudentsByCourseDTO(Set<CourseDTO> courses){

        return studentsService.getAllStudentsByCourseDTO(courses);

    }

    /**Gets students associated with the courses matching the set of courseIds
     *
     * @param courseids
     * @return
     */
    public List<StudentDTO> getAllStudentsByCourseIds(Set<Long> courseids){
       Set<Long> studentIds = studentCourseService.getStudentIdsByCourseId(courseids);
       return studentsService.getAllStudentsByIds(studentIds);

    }



}
