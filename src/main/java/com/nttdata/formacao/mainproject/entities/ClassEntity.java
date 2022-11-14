package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "average", nullable = false)
    private BigDecimal average;

    @Column(name = "duration", nullable = false)
    private BigDecimal duration;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "result", nullable = false)
    private String result;

    @ManyToOne
    @Column(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @Column(name = "professor_id", nullable = false)
    private ProfessorEntity professor;
}
