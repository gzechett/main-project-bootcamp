package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.CourseEntity;
import com.nttdata.formacao.mainproject.services.interfaces.ICourseService;
import com.nttdata.formacao.mainproject.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private CourseValidator courseValidator;

    @RequestMapping("/course")
    public ModelAndView viewCourse () {
        ModelAndView mav = new ModelAndView("view/course_view");
        mav.addObject("courseList", courseService.getAllCourses());
        return mav;
    }

    @RequestMapping("/course/save")
    public String saveCourse(@ModelAttribute("course") CourseEntity course) {
        courseValidator.validateCourse(course);
        courseService.addCourse(course);
        return "redirect:/index";
    }

    @RequestMapping("/course/new")
    public String addCourse(Model model) {
        CourseEntity course = new CourseEntity();
        model.addAttribute("course", course);
        return "new/new_course";
    }

    @RequestMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable(name = "id") int id) {
        courseService.delete(courseService.getCourse(id));
        return "redirect:/index";
    }

    @RequestMapping("/course/edit/{id}")
    public ModelAndView updateCourse(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit/edit_course");
        CourseEntity course = courseService.getCourse(id);
        mav.addObject("course", course);
        return mav;
    }
}
