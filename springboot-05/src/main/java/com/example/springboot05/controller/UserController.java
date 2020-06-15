package com.example.springboot05.controller;

import com.example.springboot05.enity.User;
import com.example.springboot05.reppsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Contended;

import java.util.List;
import java.util.Optional;
import java.util.function.LongPredicate;

@Controller
public class UserController {
    @Autowired// dependency InjectiomDi
    UserRepository userRepository;// Ah co CRUD roi!!

    @RequestMapping("/")
    public String index(Model model){
        List<User> users = (List<User>) userRepository.findAll();

        model.addAttribute("users",users);
        return "index";
    }
    @RequestMapping(value = "add")
    public String addUser(Model model){

        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user){
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editUser(@RequestParam("id") Long userId, Model model){
        Optional<User> userEdit =  userRepository.findById(userId);
        userEdit.ifPresent(user -> model.addAttribute("user", user));
        return "editUser";
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteUser(@RequestParam("id") Long userId, Model model){
        userRepository.deleteById(userId);
        return "editUser";
    }



}
