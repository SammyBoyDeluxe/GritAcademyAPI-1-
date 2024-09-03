package baller.example.GritAcademyAPI.Courses.entityandDTO;

import baller.example.GritAcademyAPI.Students.entityAndDTO.Students;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;
import java.util.Set;


@Setter
@Getter
@Entity
@Table(name = "courses")
@Component
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;




    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "courses")
    Set<Students> students;

}
