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

    //-------------Student-----------------------------------------------
    @RequestMapping("/save/student")
    public String saveStudent(@ModelAttribute("student") StudentEntity student) {
        studentValidator.validateSchoolYear(student);
        studentService.addStudent(student);
        return "redirect:/";
    }
    @RequestMapping("/new/student")
    public String addStudent(Model model){
        StudentEntity student = new StudentEntity();
        model.addAttribute("student", student);
        model.addAttribute("schoolYearList", new ArrayList<String>(Arrays.asList(
                "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
        )));
        return "/new/new_student";
    }
    @RequestMapping("/edit/student/{id}")
    public ModelAndView updateStudent(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("edit/edit_student");
        StudentEntity student = studentService.getStudent(id);
        mav.addObject("student",student);
        mav.addObject("schoolYearList", new ArrayList<String>(Arrays.asList(
                "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
        )));
        return mav;
    }
    @RequestMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable(name="id") int id){
        StudentEntity student =studentService.getStudent(id);
//        for(ClassEntity classEntity : student.getClassList()) {
//            classService.delete(classEntity);
//        }
        studentService.delete(student);
        return "redirect:/";
    }


    //--------------------Course-----------------------------

    @RequestMapping("/save/course")
    public String saveCourse(@ModelAttribute("course") CourseEntity course) {
        courseValidator.validateCourse(course);
        courseService.addCourse(course);
        return "redirect:/";
    }

    @RequestMapping("/new/course")
    public String addCourse(Model model) {
        CourseEntity course = new CourseEntity();
        model.addAttribute("course",course);
        return "new/new_course";
    }

    @RequestMapping("/delete/course/{id}")
    public String deleteCourse(@PathVariable(name="id") int id) {
        courseService.delete(courseService.getCourse(id));
        return "redirect:/";
    }

    @RequestMapping("/edit/course/{id}")
    public ModelAndView updateCourse(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_course");
        CourseEntity course = courseService.getCourse(id);
        mav.addObject("course", course);
        return mav;
    }

    //--------------------Professor-----------------------------

    @RequestMapping("/save/professor")
    public String saveProfessor(@ModelAttribute("professor") ProfessorEntity professor) {
        professorService.addProfessor(professor);
        return "redirect:/";
    }

    @RequestMapping("/new/professor")
    public String addProfessor(Model model) {
        ProfessorEntity professor = new ProfessorEntity();
        model.addAttribute("professor",professor);
        model.addAttribute("courseList",courseService.getAllCourses());
        return "/new/new_professor";
    }

    @RequestMapping("/delete/professor/{id}")
    public String deleteProfessor(@PathVariable(name="id") int id) {
        professorService.delete(professorService.getProfessor(id));
        return "redirect:/";
    }

    @RequestMapping("/edit/professor/{id}")
    public ModelAndView updateProfessor(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_professor");
        ProfessorEntity professor = professorService.getProfessor(id);
        mav.addObject("professor", professor);
        mav.addObject("courseList",courseService.getAllCourses());
        return mav;
    }

    //--------------------Class-----------------------------
    @RequestMapping("/save/class")
    public String saveClass(@ModelAttribute("class") ClassEntity classEntity) {
        classService.addClass(classEntity);
        return "redirect:/";
    }

    @RequestMapping("/new/class")
    public String addClass(Model model) {
        ClassEntity classEntity = new ClassEntity();
        model.addAttribute("class",classEntity);
        return "new/new_class";
    }

    @RequestMapping("/delete/class/{id}")
    public String deleteClass(@PathVariable(name="id") int id) {
        classService.delete(classService.getClass(id));
        return "redirect:/";
    }

    @RequestMapping("/edit/class/{id}")
    public ModelAndView updateClass(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_class");
        ClassEntity classEntity = classService.getClass(id);
        mav.addObject("class", classEntity);
        return mav;
    }


    @RequestMapping("/connect")
    public String connectApi() {
        WebClient webClient = WebClient.create("http://localhost:8082");
        List<CourseEntity> courses = webClient.get().uri("/courses").retrieve().bodyToFlux(CourseEntity.class).collectList().block();

        WebClient webClient2 = WebClient.create("http://localhost:8082");
        List<StudentEntity> studentList = webClient.get().uri("/validate/educational-year/" + 6).retrieve().bodyToFlux(StudentEntity.class).collectList().block();
        System.out.println("aaaa");

        return null;
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

//        classService.delete(classEntity);
//        System.out.println("Class deleted");
    }


}
