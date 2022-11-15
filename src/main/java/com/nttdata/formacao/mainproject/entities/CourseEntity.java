package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "area", nullable = false)
    private String area;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ProfessorEntity> professorList;
}
