package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.ProfessorEntity;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.services.interfaces.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfessorController {

    @Autowired
    private IProfessorService professorService;

    @Autowired
    private ICourseService courseService;

    @RequestMapping("/professor")
    public ModelAndView viewProfessor () {
        ModelAndView mav = new ModelAndView("view/professor_view");
        mav.addObject("professorList", professorService.getAllProfessors());
        return mav;
    }

    @RequestMapping("/professor/save")
    public String saveProfessor(@ModelAttribute("professor") ProfessorEntity professor) {
        professorService.addProfessor(professor);
        return "redirect:/index";
    }

    @RequestMapping("/professor/new")
    public String addProfessor(Model model) {
        ProfessorEntity professor = new ProfessorEntity();
        model.addAttribute("professor", professor);
        model.addAttribute("courseList", courseService.getAllCourses());
        return "/new/new_professor";
    }

    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable(name = "id") int id) {
        professorService.delete(professorService.getProfessor(id));
        return "redirect:/index";
    }

    @RequestMapping("/professor/edit/{id}")
    public ModelAndView updateProfessor(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_professor");
        ProfessorEntity professor = professorService.getProfessor(id);
        mav.addObject("professor", professor);
        mav.addObject("courseList", courseService.getAllCourses());
        return mav;
    }
}
