package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.enums.Gender;
import com.nttdata.formacao.mainproject.enums.SchoolYears;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ModelAndView viewStudent() {
        ModelAndView mav = new ModelAndView("view/student_view");
        mav.addObject("studentList", studentService.getAllStudents());
        return mav;
    }

    @PostMapping("")
    public ModelAndView saveStudent(@Valid @ModelAttribute("student") StudentEntity student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/new/new_student");
            mav.addObject("schoolYearList", SchoolYears.values());
            mav.addObject("genderList", Gender.values());
            return mav;
        }
//        studentValidatorOld.validateSchoolYear(student);
        studentService.addStudent(student);
        return new ModelAndView("redirect:/student");
    }

    @GetMapping("new")
    public String addStudent(Model model) {
        StudentEntity student = new StudentEntity();
        model.addAttribute("student", student);
        model.addAttribute("schoolYearList", SchoolYears.values());
        model.addAttribute("genderList", Gender.values());
        return "/new/new_student";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView updateStudent(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_student");
        StudentEntity student = studentService.getStudent(id);
        mav.addObject("student", student);
        mav.addObject("schoolYearList", SchoolYears.values());
        mav.addObject("genderList", Gender.values());
        return mav;
    }

    @RequestMapping("/{id}/delete") //Delete Mapping(Mas não funcionou com DELETE)
    public String deleteStudent(@PathVariable(name = "id") int id) {
        StudentEntity student = studentService.getStudent(id);
        studentService.delete(student);
        return "redirect:/student";
    }

    @PostMapping("/{id}") //PUTMapping(Mas não funcionou com PUT)
    public ModelAndView update(@PathVariable long id, @Valid @ModelAttribute("student") StudentEntity student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/edit/edit_student");
            mav.addObject("schoolYearList", SchoolYears.values());
            mav.addObject("genderList", Gender.values());
            return mav;
        }
        student.setId(id);
        studentService.addStudent(student);
        return new ModelAndView("redirect:/student");
    }
}
