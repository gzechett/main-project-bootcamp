package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.entities.StudentEntity;
import com.nttdata.formacao.mainproject.enums.Gender;
import com.nttdata.formacao.mainproject.enums.SchoolYears;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    @GetMapping("")
    public ModelAndView viewCourse () {
        ModelAndView mav = new ModelAndView("view/course_view");
        mav.addObject("courseList", courseService.getAllCourses());
        return mav;
    }

    @PostMapping("")
    public ModelAndView saveCourse(@Valid @ModelAttribute("course") CourseEntity course, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/new/new_course");
            return mav;
        }
        courseService.addCourse(course);
        return new ModelAndView("redirect:/course");
    }

    @GetMapping("new")
    public String addCourse(Model model) {
        CourseEntity course = new CourseEntity();
        model.addAttribute("course", course);
        return "new/new_course";
    }

    @RequestMapping("{id}/delete")//Delete Mapping(Mas não funcionou com DELETE)
    public String deleteCourse(@PathVariable(name = "id") int id) {
        courseService.delete(courseService.getCourse(id));
        return "redirect:/course";
    }

    @GetMapping("{id}/edit")
    public ModelAndView updateCourse(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_course");
        CourseEntity course = courseService.getCourse(id);
        mav.addObject("course", course);
        return mav;
    }

    @PostMapping("{id}") //PUTMapping(Mas não funcionou com PUT)
    public ModelAndView update(@PathVariable long id, @Valid @ModelAttribute("course") CourseEntity course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("/edit/edit_course");
            return mav;
        }
        course.setId(id);
        courseService.addCourse(course);
        return new ModelAndView("redirect:/course");
    }
}
