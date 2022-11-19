package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.services.implementation.CourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import com.nttdata.formacao.mainproject.validators.CourseValidator;
import com.nttdata.formacao.mainproject.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import com.nttdata.formacao.mainproject.entities.*;
import com.nttdata.formacao.mainproject.services.interfaces.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private StudentValidator studentValidator;

    @Autowired
    private CourseValidator courseValidator;

    @Autowired
    private IUserService userService;

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

    //-------------Student-----------------------------------------------
//    @RequestMapping("/student/save")
//    public String saveStudent(@ModelAttribute("student") StudentEntity student) {
//        studentValidator.validateSchoolYear(student);
//        studentService.addStudent(student);
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/student/new")
//    public String addStudent(Model model) {
//        StudentEntity student = new StudentEntity();
//        model.addAttribute("student", student);
//        model.addAttribute("schoolYearList", new ArrayList<String>(Arrays.asList(
//                "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
//        )));
//        return "/new/new_student";
//    }
//
//    @RequestMapping("/student/edit/{id}")
//    public ModelAndView updateStudent(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("edit/edit_student");
//        StudentEntity student = studentService.getStudent(id);
//        mav.addObject("student",student);
//        mav.addObject("schoolYearList", new ArrayList<String>(Arrays.asList(
//                "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
//        )));
//        return mav;
//    }
//
//    @RequestMapping("/student/delete/{id}")
//    public String deleteStudent(@PathVariable(name = "id") int id) {
//        StudentEntity student = studentService.getStudent(id);
//        for(ClassEntity classEntity : student.getClassList()) {
//            classService.delete(classEntity);
//        }
//        studentService.delete(student);
//        return "redirect:/index";
//    }


    //--------------------Course-----------------------------

//    @RequestMapping("/course/save")
//    public String saveCourse(@ModelAttribute("course") CourseEntity course) {
//        courseValidator.validateCourse(course);
//        courseService.addCourse(course);
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/course/new")
//    public String addCourse(Model model) {
//        CourseEntity course = new CourseEntity();
//        model.addAttribute("course", course);
//        return "new/new_course";
//    }
//
//    @RequestMapping("/course/delete/{id}")
//    public String deleteCourse(@PathVariable(name = "id") int id) {
//        courseService.delete(courseService.getCourse(id));
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/course/edit/{id}")
//    public ModelAndView updateCourse(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("edit/edit_course");
//        CourseEntity course = courseService.getCourse(id);
//        mav.addObject("course", course);
//        return mav;
//    }

    //--------------------Professor-----------------------------

//    @RequestMapping("/professor/save")
//    public String saveProfessor(@ModelAttribute("professor") ProfessorEntity professor) {
//        professorService.addProfessor(professor);
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/professor/new")
//    public String addProfessor(Model model) {
//        ProfessorEntity professor = new ProfessorEntity();
//        model.addAttribute("professor", professor);
//        model.addAttribute("courseList", courseService.getAllCourses());
//        return "/new/new_professor";
//    }
//
//    @RequestMapping("/professor/delete/{id}")
//    public String deleteProfessor(@PathVariable(name = "id") int id) {
//        professorService.delete(professorService.getProfessor(id));
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/professor/edit/{id}")
//    public ModelAndView updateProfessor(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("edit/edit_professor");
//        ProfessorEntity professor = professorService.getProfessor(id);
//        mav.addObject("professor", professor);
//        mav.addObject("courseList", courseService.getAllCourses());
//        return mav;
//    }

    //--------------------Class-----------------------------
//    @RequestMapping("/class/save")
//    public String saveClass(@ModelAttribute("class") ClassEntity classEntity) {
//        classService.addClass(classEntity);
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/class/new")
//    public String addClass(Model model) {
//        ClassEntity classEntity = new ClassEntity();
//        model.addAttribute("class", classEntity);
//        model.addAttribute("studentList",studentService.getAllStudents());
//        model.addAttribute("professorList",professorService.getAllProfessors());
//        model.addAttribute("resultOptions",new ArrayList<String>(Arrays.asList("Approved","Refused")));
//        return "new/new_class";
//    }
//
//    @RequestMapping("/delete/class/{id}")
//    public String deleteClass(@PathVariable(name = "id") int id) {
//        classService.delete(classService.getClass(id));
//        return "redirect:/index";
//    }
//
//    @RequestMapping("/class/edit/{id}")
//    public ModelAndView updateClass(@PathVariable(name = "id") int id) {
//        ModelAndView mav = new ModelAndView("edit/edit_class");
//        ClassEntity classEntity = classService.getClass(id);
//        mav.addObject("class", classEntity);
//        mav.addObject("studentList",studentService.getAllStudents());
//        mav.addObject("professorList",professorService.getAllProfessors());
//        return mav;
//    }

//    @RequestMapping("/login/save")
//    public String saveUser(@ModelAttribute(name = "user") UserEntity user) {
//        return null;
//    }
//


//    @RequestMapping("/user/verify")
//    public String verifyUser(@ModelAttribute(name = "user") UserEntity user){
//        if(!userService.existUser(user)){
//            return "redirect:/";
//        }
//        return "redirect:/index";
//    }

//    @RequestMapping("/student")
//    public ModelAndView viewStudent () {
//        ModelAndView mav = new ModelAndView("view/student_view");
//        mav.addObject("studentList", studentService.getAllStudents());
//        return mav;
//    }
//    @RequestMapping("/professor")
//    public ModelAndView viewProfessor () {
//        ModelAndView mav = new ModelAndView("view/professor_view");
//        mav.addObject("professorList", professorService.getAllProfessors());
//        return mav;
//    }
//    @RequestMapping("/course")
//    public ModelAndView viewCourse () {
//        ModelAndView mav = new ModelAndView("view/course_view");
//        mav.addObject("courseList", courseService.getAllCourses());
//        return mav;
//    }
//    @RequestMapping("/class")
//    public ModelAndView viewClass () {
//        ModelAndView mav = new ModelAndView("view/class_view");
//        mav.addObject("classList", classService.getAllClasses());
//        return mav;
//    }

    @PostConstruct
    public void callMethodsToTestServices() {

        this.studentServiceTest();
        this.courseServiceTest();
        this.professorServiceTest();
        this.classServiceTest();

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword("pass");
        userService.saveUser(user);
    }

    public void studentServiceTest() {

        StudentEntity student = new StudentEntity();
        student.setName("Student 1");
        student.setCc("123456789");
        student.setRegistrationNumber("00000001");
        student.setAge(12);
        student.setGender("M");
        student.setSchoolYear("1st");
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
            classEntity.setDuration(60);
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

//        classService.delete(classEntity);
//        System.out.println("Class deleted");
    }


}
