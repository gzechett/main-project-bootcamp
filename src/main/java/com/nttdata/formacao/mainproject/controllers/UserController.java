package com.nttdata.formacao.mainproject.controllers;

import com.nttdata.formacao.mainproject.entities.UserEntity;
import com.nttdata.formacao.mainproject.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/user/verify")
    public String verifyUser(@ModelAttribute(name = "user") UserEntity user){
        if(!userService.existUser(user)){
            return "redirect:/";
        }
        return "redirect:/index";
    }

    @RequestMapping("/login/save")
    public String saveUser(@ModelAttribute(name = "user") UserEntity user) {
        return null;
    }
}
