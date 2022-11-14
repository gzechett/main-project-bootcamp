package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IClassService classService;


    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<StudentEntity> studentList = studentService.getAllStudents();
        model.addAttribute("studentList",studentList);

        List<ProfessorEntity> professorList = professorService.getAllProfessors();
        model.addAttribute("professorList",professorList);

        List<CourseEntity> courseList = courseService.getAllCourses();
        model.addAttribute("courseList",courseList);

        List<ClassEntity> classList = classService.getAllClasses();
        model.addAttribute("classList",classList);

        return "index";
    }
    @PostConstruct
    public void callMethodsToTestServices() {

        this.studentServiceTest();
        this.courseServiceTest();
        this.professorServiceTest();
        this.classServiceTest();
    }

    public void studentServiceTest() {

        StudentEntity student = new StudentEntity();
        student.setName("Student 1");
        student.setCc("123456789");
        student.setRegistrationNumber("00000001");
        student.setAge(12);
        student.setGender("M");
        studentService.addStudent(student);
        System.out.println("Student added");

        System.out.println("Get Student: " + studentService.getStudent(1));

        System.out.println("GetAllStudents:");
        for (StudentEntity studentItr : studentService.getAllStudents()) {
            System.out.println(studentItr);
        }

//        studentService.delete(student);
//        System.out.println("Student deleted");
    }


    public void professorServiceTest() {

        CourseEntity course = courseService.getCourse(1);

        ProfessorEntity professor = new ProfessorEntity();
        professor.setName("Professor 1");
        professor.setCc("987654321");
        professor.setAge(40);
        professor.setGender("M");
        professor.setSalary(BigDecimal.valueOf(3200));
        professor.setTeachingTime(7);
        professor.setCourse(course);
        professorService.addProfessor(professor);
        System.out.println("Professor added");

        System.out.println("Get Professor: " + professorService.getProfessor(1));

        System.out.println("GetAllProfessors:");
        for (ProfessorEntity professorItr : professorService.getAllProfessors()) {
            System.out.println(professorItr);
        }

//        professorService.delete(professor);
//        System.out.println("Professor deleted");
    }

    public void courseServiceTest() {

        CourseEntity course = new CourseEntity();
        course.setName("Matematica");
        course.setArea("Exatas");
        courseService.addCourse(course);
        System.out.println("Course added");

        System.out.println("Get Course: " + courseService.getCourse(1));

        System.out.println("GetAllCourses:");
        for (CourseEntity courseItr : courseService.getAllCourses()) {
            System.out.println(courseItr);
        }

//        courseService.delete(course);
//        System.out.println("Course deleted");
    }

    public void classServiceTest() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StudentEntity student = studentService.getStudent(1);
        ProfessorEntity professor = professorService.getProfessor(1);

        ClassEntity classEntity = new ClassEntity();
        try {
            classEntity.setProfessor(professor);
            classEntity.setStudent(student);
            classEntity.setDate(sdf.parse("2022-04-23"));
            classEntity.setDuration(BigDecimal.valueOf(60));
            classEntity.setValue(BigDecimal.valueOf(60));
            classEntity.setAverage(BigDecimal.valueOf(6));
            classEntity.setResult("Approved");
            classService.addClass(classEntity);
            System.out.println("Class added");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Get Class: " + classService.getClass(1));

        System.out.println("GetAllClass:");
        for (ClassEntity classItr : classService.getAllClasses()) {
            System.out.println(classItr);
        }

        classService.delete(classEntity);
        System.out.println("Class deleted");
    }


}
