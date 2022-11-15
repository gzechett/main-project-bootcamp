package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false, length = 8, unique = true)
    private String registrationNumber;

    @Column(name = "cc", nullable = false, unique = true, length = 9)
    private String cc;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false, columnDefinition = "CHAR")
    private String gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<ClassEntity> classList;
}
