package com.nttdata.formacao.mainproject.entities;

import com.nttdata.formacao.mainproject.annotations.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Course
@Entity
@Table(name = "Course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "area", nullable = false)
    @NotNull
    @NotBlank
    private String area;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<ProfessorEntity> professorList;
}
