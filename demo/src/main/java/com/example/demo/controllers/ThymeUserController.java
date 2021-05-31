package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ThymeUserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("user", new User()); // New User to add
        model.addAttribute("users", userRepository);
        return "users";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user){
        //user.getId();
        if(userRepository.containsKey(user.getName())){
            userRepository.remove(user.getName());
        } else {
            userRepository.put(user.getName(),user);
        }
        return "thankyou";
    }


}
