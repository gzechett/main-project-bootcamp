package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.enums.ClassResult;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ClassController {

    @Autowired
    private IClassService classService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IProfessorService professorService;

    @RequestMapping("/class")
    public ModelAndView viewClass () {
        ModelAndView mav = new ModelAndView("view/class_view");
        mav.addObject("classList", classService.getAllClasses());
        return mav;
    }

    @RequestMapping("/class/save")
    public String saveClass(@ModelAttribute("class") ClassEntity classEntity) {
        classService.addClass(classEntity);
        return "redirect:/class";
    }

    @RequestMapping("/class/new")
    public String addClass(Model model) {
        ClassEntity classEntity = new ClassEntity();
        model.addAttribute("class", classEntity);
        model.addAttribute("studentList",studentService.getAllStudents());
        model.addAttribute("professorList",professorService.getAllProfessors());
//        model.addAttribute("resultOptions",new ArrayList<String>(Arrays.asList("Approved","Refused")));
        model.addAttribute("resultOptions", ClassResult.values());
        return "new/new_class";
    }

    @RequestMapping("/class/delete/{id}")
    public String deleteClass(@PathVariable(name = "id") int id) {
        classService.delete(classService.getClass(id));
        return "redirect:/class";
    }

    @RequestMapping("/class/edit/{id}")
    public ModelAndView updateClass(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_class");
        ClassEntity classEntity = classService.getClass(id);
        mav.addObject("class", classEntity);
        mav.addObject("studentList",studentService.getAllStudents());
        mav.addObject("professorList",professorService.getAllProfessors());
        mav.addObject("resultOptions", ClassResult.values());
        return mav;
    }
}
