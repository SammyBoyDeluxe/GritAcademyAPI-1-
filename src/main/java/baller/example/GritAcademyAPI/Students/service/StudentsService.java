package baller.example.GritAcademyAPI.Students.service;

import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Students.entityAndDTO.StudentDTO;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import baller.example.GritAcademyAPI.Students.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;


    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;

    }


    /**Gets student associated with a studentid
     *
     * @param id
     * @return studentsAsDTOs
     */
    public Optional<StudentDTO> getStudentById(long id) {
        return studentsRepository.findById(id).map(StudentDTO::mapToDTO);

    }
    /**Gets student associated with a studentid and its associated courses
     *
     * @param id
     * @return studentsAsDTOsWithCourses
     */
    public Optional<StudentDTO> getStudentByIdWithCourses(long id) {
        return studentsRepository.findById(id).map(StudentDTO::mapToDTOWithCourses);

    }

    /**Gets student-table
     *
     * @return students
     */
    public List<Students> getTable() {
        return studentsRepository.findAll();
    }

    /**Gets filtered table by attributes without courses
     *
     * @param fname
     * @param lname
     * @param town
     * @return studentsByAttributes
     */
    public List<StudentDTO> getStudentsByAttributes(@RequestParam(required = false, name = "fname" ) String fname, @RequestParam(name = "lname",required = false) String lname, @RequestParam(name = "town", required = false)String town) {
        /*Request-parameters are null if they don´t exist, we see what */

        if(fname != null && lname != null && town != null){
            List<Students> students =  studentsRepository.findByFnameAndLnameAndTown(fname,lname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        }
        else if(fname != null && lname != null){
            List<Students> students = studentsRepository.findByFnameAndLname(fname,lname);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        } else if(fname != null && town != null){
            List<Students> students = studentsRepository.findByFnameAndTown(fname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());

        } else if(lname!= null && town != null){
            List<Students> students = studentsRepository.findByLnameAndTown(lname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        }
        else if(fname != null){
            List<Students> students = studentsRepository.findByFname(fname);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        }
        else if (lname != null){

            List<Students> students = studentsRepository.findByLname(lname);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        } else if(town != null){
            List<Students> students = studentsRepository.findByTown(town);
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());

        }
        else {

            List<Students> students =  studentsRepository.findAll();
            return students.stream()
                    .map(StudentDTO::mapToDTO)
                    .collect(Collectors.toList());
        }

    }

    /**Gets students by attributes with courses -> Null case: Entire table
     *
     * @param fname
     * @param lname
     * @param town
     * @return
     */
    public List<StudentDTO> getStudentsByAttributesWithCourses(String fname, String lname, @RequestParam(name = "town", required = false)String town) {
        /*Request-parameters are null if they don´t exist, we see what */

        if(fname != null && lname != null && town != null){
            List<Students> students =  studentsRepository.findByFnameAndLnameAndTown(fname,lname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        }
        else if(fname != null && lname != null){
            List<Students> students = studentsRepository.findByFnameAndLname(fname,lname);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        } else if(fname != null && town != null){
            List<Students> students = studentsRepository.findByFnameAndTown(fname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());

        } else if(lname!= null && town != null){
            List<Students> students = studentsRepository.findByLnameAndTown(lname,town);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        }
        else if(fname != null){
            List<Students> students = studentsRepository.findByFname(fname);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        }
        else if (lname != null){

            List<Students> students = studentsRepository.findByLname(lname);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        } else if(town != null){
            List<Students> students = studentsRepository.findByTown(town);
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());

        }
        else {

            List<Students> students =  studentsRepository.findAll();
            return students.stream()
                    .map(StudentDTO::mapToDTOWithCourses)
                    .collect(Collectors.toList());
        }

    }


    /**Returns a list of students matching the courseDTO-model
     *
     * @param course
     * @return studentsByCourseDto
     */
    public List<StudentDTO> getAllStudentsByCourseDTO(Set<CourseDTO> course){
       Set<Courses> courseEntity = course.stream().map(CourseDTO::mapToEntity).collect(Collectors.toSet());
        return studentsRepository.findStudentsByCourses(courseEntity).stream().map(StudentDTO::mapToDTO).collect(Collectors.toList());


    }

    /**Returns a list of students matching the course-model
     *
     * @param course
     * @return studentsByCourse
     */
    public List<StudentDTO> getAllStudentsInCourse(Set<Courses> course){
        return studentsRepository.findStudentsByCourses(course).stream().map(StudentDTO::mapToDTO).collect(Collectors.toList());


    }

    /**Gets a list of studentDTOs modeling the entities with the selected ids
     *
     * @param studentids
     * @return listOfStudentsByIds
     */
    public List<StudentDTO> getAllStudentsByIds(Set<Long> studentids){
        return studentsRepository.findStudentsByIdIn(studentids).stream().map(StudentDTO::mapToDTO).collect(Collectors.toList());


    }


}
