package com.pro.project.controller;

import com.pro.project.entity.Dept;
import com.pro.project.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class DeptAdminController {
    @Autowired
    DeptService deptService;

    @GetMapping("/ub")
    public String ub(HttpServletRequest request, Model model){

    Long deptno = 1L;


        Long preWorkCount1100 = deptService.countPreWorkByDeptNo1100(deptno);  //출근전 카운트
        Long countWork1100 = deptService.countWorkByDeptNo1100(deptno);
        Long countLeave1100 = deptService.countLeaveWorkByDeptNo1100(deptno);
        Long countCommute1100 = deptService.countCommuteByDeptNo1100(deptno);
        Long countVacation1100 = deptService.countVacationByDeptNo1100(deptno);
        model.addAttribute("preWorkCount1100", preWorkCount1100);
        model.addAttribute("countWork1100", countWork1100);
        model.addAttribute("countLeave1100", countLeave1100);
        model.addAttribute("countCommute1100", countCommute1100);
        model.addAttribute("countVacation1100", countVacation1100);


        Long preWorkCount1200 = deptService.countPreWorkByDeptNo1200(deptno);  //출근전 카운트
        Long countWork1200 = deptService.countWorkByDeptNo1200(deptno);
        Long countLeave1200 = deptService.countLeaveWorkByDeptNo1200(deptno);
        Long countCommute1200 = deptService.countCommuteByDeptNo1200(deptno);
        Long countVacation1200 = deptService.countVacationByDeptNo1200(deptno);


        model.addAttribute("preWorkCount1200", preWorkCount1200);
        model.addAttribute("countWork1200", countWork1200);
        model.addAttribute("countLeave1200", countLeave1200);
        model.addAttribute("countCommute1200", countCommute1200);
        model.addAttribute("countVacation1200", countVacation1200);
        return "ub";
    }
}
