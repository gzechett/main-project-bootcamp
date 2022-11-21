package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ClassEntity;
import com.nttdata.formacao.mainproject.enums.ClassResult;
import com.nttdata.formacao.mainproject.services.interfaces.IClassService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import com.nttdata.formacao.mainproject.services.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "class")
public class ClassController {

    @Autowired
    private IClassService classService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IProfessorService professorService;

    @GetMapping("")
    public ModelAndView viewClass () {
        ModelAndView mav = new ModelAndView("view/class_view");
        mav.addObject("classList", classService.getAllClasses());
        return mav;
    }

    @PostMapping("")
    public ModelAndView saveClass(@Valid @ModelAttribute("class") ClassEntity classEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/new/new_class");
            mav.addObject("studentList",studentService.getAllStudents());
            mav.addObject("professorList",professorService.getAllProfessors());
            mav.addObject("resultOptions", ClassResult.values());
            return mav;
        }
        classService.addClass(classEntity);
        return new ModelAndView("redirect:/class");
    }

    @GetMapping("/new")
    public String addClass(Model model) {
        ClassEntity classEntity = new ClassEntity();
        model.addAttribute("class", classEntity);
        model.addAttribute("studentList",studentService.getAllStudents());
        model.addAttribute("professorList",professorService.getAllProfessors());
        model.addAttribute("resultOptions", ClassResult.values());
        return "new/new_class";
    }

    @RequestMapping("{id}/delete")
    public String deleteClass(@PathVariable(name = "id") int id) {
        classService.delete(classService.getClass(id));
        return "redirect:/class";
    }

    @GetMapping("{id}/edit")
    public ModelAndView updateClass(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_class");
        ClassEntity classEntity = classService.getClass(id);
        mav.addObject("class", classEntity);
        mav.addObject("studentList",studentService.getAllStudents());
        mav.addObject("professorList",professorService.getAllProfessors());
        mav.addObject("resultOptions", ClassResult.values());
        return mav;
    }

    @PostMapping("/{id}") //PUTMapping(Mas não funcionou com PUT)
    public ModelAndView update(@PathVariable long id, @Valid @ModelAttribute("class") ClassEntity classEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/edit/edit_class");
            mav.addObject("studentList",studentService.getAllStudents());
            mav.addObject("professorList",professorService.getAllProfessors());
            mav.addObject("resultOptions", ClassResult.values());
            return mav;
        }
        classEntity.setId(id);
        classService.addClass(classEntity);
        return new ModelAndView("redirect:/class");
    }
}
