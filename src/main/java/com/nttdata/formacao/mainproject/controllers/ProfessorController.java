package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.enums.Gender;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "professor")
public class ProfessorController {

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public ModelAndView viewProfessor () {
        ModelAndView mav = new ModelAndView("view/professor_view");
        mav.addObject("professorList", professorService.getAllProfessors());
        return mav;
    }

    @PostMapping("")
    public ModelAndView saveProfessor(@Valid @ModelAttribute("professor") ProfessorEntity professor, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/new/new_professor");
            mav.addObject("courseList", courseService.getAllCourses());
            mav.addObject("genderList", Gender.values());
            return mav;
        }
        professorService.addProfessor(professor);
        return new ModelAndView("redirect:/professor");
    }

    @GetMapping("new")
    public String addProfessor(Model model) {
        ProfessorEntity professor = new ProfessorEntity();
        model.addAttribute("professor", professor);
        model.addAttribute("courseList", courseService.getAllCourses());
        model.addAttribute("genderList", Gender.values());
        return "/new/new_professor";
    }

    @RequestMapping("{id}/delete")
    public String deleteProfessor(@PathVariable(name = "id") int id) {
        professorService.delete(professorService.getProfessor(id));
        return "redirect:/professor";
    }

    @GetMapping("{id}/edit")
    public ModelAndView updateProfessor(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_professor");
        ProfessorEntity professor = professorService.getProfessor(id);
        mav.addObject("professor", professor);
        mav.addObject("courseList", courseService.getAllCourses());
        mav.addObject("genderList", Gender.values());
        return mav;
    }

    @PostMapping("{id}") //PUTMapping(Mas não funcionou com PUT)
    public ModelAndView update(@PathVariable long id, @Valid @ModelAttribute("professor") ProfessorEntity professor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/edit/edit_professor");
            mav.addObject("courseList", courseService.getAllCourses());
            mav.addObject("genderList", Gender.values());
            return mav;
        }
        professor.setId(id);
        professorService.addProfessor(professor);
        return new ModelAndView("redirect:/professor");
    }
}
