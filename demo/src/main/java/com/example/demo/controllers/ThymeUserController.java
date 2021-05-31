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

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("user", new User()); // New User to add
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @PostMapping("/subscribe")
    public String subscribeUser(@ModelAttribute("user") User user){
        try{
            userRepository.save(user);
        } catch (Exception e){
            return "duplicate";
        }
        return "thankyou";
    }

    @PostMapping("/unsubscribe")
    public String UnsubscribeUser(@ModelAttribute("user") User user){
        try{
            User find_user = userRepository.findByNameAndEmail(user.getName(),user.getEmail());
            userRepository.delete(find_user);
        } catch (Exception e){
            return "notFind";
        }

        return "thankyou";
    }


}
