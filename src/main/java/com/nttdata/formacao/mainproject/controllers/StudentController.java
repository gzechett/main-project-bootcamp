package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.enums.Gender;
import com.nttdata.formacao.mainproject.enums.SchoolYears;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import com.nttdata.formacao.mainproject.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private StudentValidator studentValidator;

    @RequestMapping("/student")
    public ModelAndView viewStudent () {
        ModelAndView mav = new ModelAndView("view/student_view");
        mav.addObject("studentList", studentService.getAllStudents());
        return mav;
    }

    @RequestMapping("/student/save")
    public String saveStudent(@ModelAttribute("student") StudentEntity student) {
        studentValidator.validateSchoolYear(student);
        studentService.addStudent(student);
        return "redirect:/student";
    }

    @RequestMapping("/student/new")
    public String addStudent(Model model) {
        StudentEntity student = new StudentEntity();
        model.addAttribute("student", student);
        model.addAttribute("schoolYearList", SchoolYears.values());
        model.addAttribute("genderList", Gender.values());
        return "/new/new_student";
    }

    @RequestMapping("/student/edit/{id}")
    public ModelAndView updateStudent(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_student");
        StudentEntity student = studentService.getStudent(id);
        mav.addObject("student",student);
//        mav.addObject("schoolYearList", new ArrayList<String>(Arrays.asList(
//                "1st","2nd","3rd","4th","5th","6th","7th","8th","9th","10th","11th","12th"
//        )));
        mav.addObject("schoolYearList", SchoolYears.values());
        mav.addObject("genderList", Gender.values());
        return mav;
    }

    @RequestMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") int id) {
        StudentEntity student = studentService.getStudent(id);
//        for(ClassEntity classEntity : student.getClassList()) {
//            classService.delete(classEntity);
//        }
        studentService.delete(student);
        return "redirect:/student";
    }
}
