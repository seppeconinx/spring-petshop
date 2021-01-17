package be.ehb.dierenwinkel.controllers;

import be.ehb.dierenwinkel.models.User;
import be.ehb.dierenwinkel.repositories.UserRepository;
import be.ehb.dierenwinkel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @ModelAttribute("newUser")
    public User registerNewUser(){
        return new User();
    }

    //Show register page
    @GetMapping(value ="/register")
    public String showRegister(Model model) {
        return "register";
    }

    //Register account
    @PostMapping(value="/register")
    public String registerUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, HttpServletRequest request){
        //Check if already an account with this email
        if(userRepository.getByEmail(user.getEmail()) != null){
            bindingResult.rejectValue("email", "There is already an account registered with this email!");
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.save(user);

        //Login after register
        try {
            request.login(user.getEmail(), user.getPassword());
        } catch (ServletException e) {
            System.out.println(e);
        }
        return "redirect:/";
    }

}
