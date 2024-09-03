package baller.example.GritAcademyAPI.Students.entityAndDTO;


import baller.example.GritAcademyAPI.Courses.entityandDTO.Courses;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@Component
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "fname")
    String fname;
    @Column(name = "lname")
    String lname;
    @Column(name = "town")
    String town;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses",joinColumns = @JoinColumn(name = "studentid"),inverseJoinColumns = @JoinColumn(name = "courseid"))
    Set<Courses> courses;

}
