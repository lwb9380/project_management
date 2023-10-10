package com.pro.project.controller;

import com.pro.project.dto.Day;
import com.pro.project.dto.ScheduleRequest;
import com.pro.project.service.ScheduleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@Log4j2
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    //임시페이지
    @GetMapping("/schedule")
    public String scheduleController(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        model.addAttribute("empno", empno);
        return "schedule/scheduleController";
    }

    //임시로 스케줄 테이블 삭제하기
    @GetMapping("/deleteSchedule")
    public String deleteSchedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        scheduleService.deleteSchedule(empno);
        return "redirect:/schedule";
    }

    //임시로 스케줄 신청 테이블 삭제하기
    @GetMapping("/deleteScheduleRequest")
    public String deleteScheduleRequest(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        scheduleService.deleteScheduleRequest(empno);
        return "redirect:/schedule";
    }


    //간단 스케줄 조회
    @GetMapping("/showSchedule")
    public String showSchedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        Day day = scheduleService.selectDaycheck(empno);
        if(day!=null){
            int monday = day.getMonday();
            int tuesday = day.getTuesday();
            int wednesday = day.getWednesday();
            int thursday = day.getThursday();
            int friday = day.getFriday();
            model.addAttribute("monday", monday);
            model.addAttribute("tuesday", tuesday);
            model.addAttribute("wednesday", wednesday);
            model.addAttribute("thursday", thursday);
            model.addAttribute("friday", friday);
        }

        model.addAttribute("empno", empno);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "schedule/showSimpleSchedule";
    }

    //간단 스케줄 신청 조회
    @GetMapping("/showScheduleRequest")
    public String showScheduleRequest(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        ScheduleRequest scheduleRequest = scheduleService.selectRequest(empno);
        if(scheduleRequest!=null){
            int monday = scheduleRequest.getMonday();
            int tuesday = scheduleRequest.getTuesday();
            int wednesday = scheduleRequest.getWednesday();
            int thursday = scheduleRequest.getThursday();
            int friday = scheduleRequest.getFriday();
            model.addAttribute("monday", monday);
            model.addAttribute("tuesday", tuesday);
            model.addAttribute("wednesday", wednesday);
            model.addAttribute("thursday", thursday);
            model.addAttribute("friday", friday);
        }

        model.addAttribute("empno", empno);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "schedule/showScheduleRequest";
    }

    //스케쥴 바로 등록
    @GetMapping("/insertSchedule")
    public String insertSchedule(HttpServletRequest request, Model model) {
        //세션 불러와서 empno에 저장, 현재 세션은 empno밖에 없음.
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        //empno를 모델로 전달(인서트때 사용하기 위함)
        model.addAttribute("empno", empno);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        //폼
        return "schedule/insertSchedule";
    }
    @PostMapping("/insertSchedule")
    public String intSchedule(HttpServletRequest request, Model model,
                              @RequestParam("monday") int monday, @RequestParam("tuesday") int tuesday,
                              @RequestParam("wednesday") int wednesday, @RequestParam("thursday")int thursday,
                              @RequestParam("friday") int friday){
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        model.addAttribute("monday", monday);
        model.addAttribute("tuesday", tuesday);
        model.addAttribute("wednesday", wednesday);
        model.addAttribute("thursday", thursday);
        model.addAttribute("friday", friday);
        model.addAttribute("month", month);
        model.addAttribute("empno", empno);
        model.addAttribute("year", year);

        scheduleService.insertSchedule(empno, monday, tuesday, wednesday, thursday, friday, year, month);
        return "schedule/insertScheduleCheck";
    }

    //이번달 없는 사원 스케줄 신청
    @GetMapping("/newSchedule")
    public String newSchedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        model.addAttribute("empno", empno);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "schedule/newSchedule";
    }
    @PostMapping("/newSchedule")
    public String newSchedulePost(HttpServletRequest request,
                                  @RequestParam("monday") int monday, @RequestParam("tuesday") int tuesday,
                                  @RequestParam("wednesday") int wednesday, @RequestParam("thursday") int thursday,
                                  @RequestParam("friday") int friday) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        scheduleService.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
        return "redirect:/showSchedule";
    }

    //미래의 스케줄 신청하기
    @GetMapping("/updateSchedule")
    public String updateSchedule(HttpServletRequest request, Model model) {
        return "";
    }

    @PostMapping("/updateSchedule")
    public String updateSchedule(HttpServletRequest request,
                                 @RequestParam("monday") int monday, @RequestParam("tuesday") int tuesday,
                                 @RequestParam("wednesday") int wednesday, @RequestParam("thursday") int thursday,
                                 @RequestParam("friday") int friday){
        return "";
    }

}
