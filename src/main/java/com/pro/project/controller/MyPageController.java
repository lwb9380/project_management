package com.pro.project.controller;

import com.pro.project.entity.Emp;
import com.pro.project.repository.MyPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    private MyPageRepository myPageRepository;

    public MyPageController(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    @GetMapping("page")
    public String MyPage(Model model) {
        List<Emp> myPageInfoList = myPageRepository.findAll();
        model.addAttribute("list",myPageInfoList);
    return "page";
    }
}
