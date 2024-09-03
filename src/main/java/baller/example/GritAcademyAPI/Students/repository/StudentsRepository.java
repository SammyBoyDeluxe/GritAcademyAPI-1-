package baller.example.GritAcademyAPI.Students.repository;

import baller.example.GritAcademyAPI.Courses.entityandDTO.CourseDTO;
import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StudentsRepository extends JpaRepository<Students,Long> {
    List<Students> findByFname(String fname);
    List<Students> findByLname(String lname);
    List<Students> findByFnameAndLname(String fname, String lname);
    List<Students> findByFnameAndLnameAndTown(String fname, String lname, String town);
    List<Students> findByTown( String town);
    List<Students> findByFnameAndTown(String fname, String town);
    List<Students> findByLnameAndTown(String lname, String town);
    @Autowired
    List<Students> findStudentsByCourses(Set<Courses> courses);

    List<Students> findStudentsByIdIn(Set<Long> studentsids);
}
