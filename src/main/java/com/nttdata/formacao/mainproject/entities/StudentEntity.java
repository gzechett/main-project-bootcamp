package com.nttdata.formacao.mainproject.entities;

import com.nttdata.formacao.mainproject.annotations.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Student")
@Student
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false, length = 8, unique = true)
    @NotNull
    @NotBlank
    private String registrationNumber;

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
    @Min(value = 6, message = "The student must be at least 6 years old ")
    private Integer age;

    @Column(name="educational_year", nullable = false)
    private String schoolYear;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<ClassEntity> classList;
}
