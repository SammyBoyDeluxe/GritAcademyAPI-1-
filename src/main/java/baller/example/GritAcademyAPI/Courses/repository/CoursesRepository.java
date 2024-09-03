package baller.example.GritAcademyAPI.Courses.repository;

import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    /**Gets a list of Courses-objects from the database by courses.name = name
     *
     * @param name
     * @return courseWithSpecifiedName
     */
    public List<Courses> findCoursesByName(String name);

    /**Gets a list of courses with any partial match of their name attribute
     *  to the nameFragment, not case-sensitive
     * @param nameFragment
     * @return listOfCourses
     */
    public List<Courses> findCoursesByNameContainingIgnoreCase( String nameFragment);

    /**Gets courses with partial matches to their name-attributes and description-attributes
     *
     * @param nameFragment
     * @param descriptionFragment
     * @return CoursesByNameAndDescriptionContaining
     */
    public List<Courses> findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCase(String nameFragment, String descriptionFragment);

    /**Gets courses with partial matches to their description attributes with the fragment
     *
     * @param descriptionFragment
     * @return coursesWithPartialMatchToDescriptionFragment
     */
    public List<Courses> findCoursesByDescriptionContainingIgnoreCase(String descriptionFragment);


}
