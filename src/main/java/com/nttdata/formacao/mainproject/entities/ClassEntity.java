package com.nttdata.formacao.mainproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false, columnDefinition = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;
}
