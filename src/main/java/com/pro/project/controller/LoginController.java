package com.pro.project.controller;

import com.pro.project.auth.AuthService;
import com.pro.project.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private AuthService authService;
//    public void setAuthService(AuthService authService) {
//        this.authService = authService;
//    }

    @GetMapping("/login")
    public String form(LoginCommand loginCommand,
                       @CookieValue(value = "dog", required = false) Cookie rCookie) {
        System.out.println(rCookie);
        if (rCookie != null) {
            loginCommand.setEmpno(Long.valueOf(rCookie.getValue()));
            loginCommand.isRememberEmpno(true);
        }
        if (loginCommand == null) {
            loginCommand = new LoginCommand();
        }

        return "login/loginForm";
    }

    @PostMapping("/login")
    public String submit(
            LoginCommand loginCommand, Errors errors, HttpSession session,
            HttpServletResponse response) {
        new LoginCommandValidator().validate(loginCommand, errors);

        if (errors.hasErrors()) {
            return "login/loginForm";
        }
        try {
            boolean authInfo = authService.authenticate(
                    loginCommand.getEmpno(),
                    loginCommand.getPassword());

            session.setAttribute("authInfo", authInfo); // 로그인을 성공하면 인증정보 객체를 저장

            // 쿠키의 유효기간 설정
            Cookie rememberCookie = new Cookie("dog", "" + loginCommand.getEmpno());
            System.out.println(rememberCookie);
            rememberCookie.setPath("/");
            if (loginCommand.isRememberEmpno(loginCommand.getRememberEmpno())) {
                // 30일 동안 쿠키를 유지하도록 설정
                rememberCookie.setMaxAge(60 * 60 * 24 * 30);
            } else {
                // 쿠키를 세션 기간 동안 유지하도록 설정
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);

            session.setAttribute("user", loginCommand.getEmpno());
            session.setAttribute("password", loginCommand.getPassword());

            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }

}