package com.pro.project.controller;

import com.pro.project.auth.AuthService;
import com.pro.project.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String form(LoginCommand loginCommand, HttpSession session) {
        if (loginCommand == null) {
            loginCommand = new LoginCommand();
        }
        if(session.getAttribute("authInfo")!=null) {
            if((boolean) session.getAttribute("authInfo")){
                return "login/loginSuccess";
            }
            return "index";
        }
        return "index";
    }


    @PostMapping("/login")
    public String submit(
            LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletRequest request) {
        new LoginCommandValidator().validate(loginCommand, errors);

        if (errors.hasErrors()) {
            return "index";
        }
        try {
            boolean authInfo = authService.authenticate(
                    loginCommand.getEmpno(),
                    loginCommand.getPassword());
            session.setAttribute("authInfo", authInfo);
            session.setAttribute("user", loginCommand.getEmpno());

            return "login/loginSuccess";

        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "index";
        }

    }
    }