package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Professor")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "cc", nullable = false, unique = true, length = 9)
    private String cc;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false, columnDefinition = "CHAR")
    private String gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "teaching_time")
    private int teachingTime;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
}
