package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Professor")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cc", nullable = false, unique = true, length = 9)
    @NotNull
    @NotBlank
    private String cc;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "gender", nullable = false, columnDefinition = "CHAR")
    private String gender;

    @Column(name = "age", nullable = false)
    @NotNull
    @Min(value = 18, message = "The professor must be at least 18 years old ")
    private Integer age;

    @Column(name = "teaching_time")
    @NotNull
    private Integer teachingTime;

    @Column(name = "salary", nullable = false)
    @NotNull
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<ClassEntity> classList;
}
