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
        // 로그인된 사용자의 경우 메인 페이지로 리다이렉트
//        if (!(boolean)session.getAttribute("authInfo")) {
//            return "login/loginForm";
//        } else {
//            return "login/loginSuccess";
//        }
        if(session.getAttribute("authInfo")!=null) {
            if((boolean) session.getAttribute("authInfo")){
                return "login/loginSuccess";
            }
            return "login/loginForm";
        }
        return "login/loginForm";
    }


    @PostMapping("/login")
    public String submit(
            LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletRequest request) {
        new LoginCommandValidator().validate(loginCommand, errors);

        if (errors.hasErrors()) {
            return "login/loginForm";
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
            return "login/loginForm";
        }

    }
    }