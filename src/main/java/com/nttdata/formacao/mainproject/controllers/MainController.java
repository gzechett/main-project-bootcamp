package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import com.nttdata.formacao.mainproject.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nttdata.formacao.mainproject.entities.*;
import com.nttdata.formacao.mainproject.services.interfaces.*;

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
    public String login(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping("/index")
    public String viewHomePage(Model model) {
        List<StudentEntity> studentList = studentService.getAllStudents();
        model.addAttribute("studentList", studentList);

        List<ProfessorEntity> professorList = professorService.getAllProfessors();
        model.addAttribute("professorList", professorList);

        List<CourseEntity> courseList = courseService.getAllCourses();
        model.addAttribute("courseList", courseList);

        List<ClassEntity> classList = classService.getAllClasses();
        model.addAttribute("classList", classList);

        return "index";
    }
}
