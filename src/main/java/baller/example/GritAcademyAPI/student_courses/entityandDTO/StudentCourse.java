package baller.example.GritAcademyAPI.student_courses.entityandDTO;

import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "student_courses")
@Component
@Getter
@Setter
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "studentid")
    Students students;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "courseid")
    Courses courses;


}
