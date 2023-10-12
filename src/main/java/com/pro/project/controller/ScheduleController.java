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
import java.util.List;

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

        String firstCase = "8시출근 - 17시퇴근";
        String secondCase = "9시출근 - 18시퇴근";
        String thirdCase = "10시출근 - 19시퇴근";


        Day day = scheduleService.selectDaycheck(empno);
        if(day!=null){
            int monday = day.getMonday();
            switch(monday) {
                case 1:
                    model.addAttribute("monday", firstCase);
                    break;
                case 2:
                    model.addAttribute("monday", secondCase);
                    break;
                case 3:
                    model.addAttribute("monday", thirdCase);
                    break;
            }
            int tuesday = day.getTuesday();
            switch(tuesday) {
                case 1:
                    model.addAttribute("tuesday", firstCase);
                    break;
                case 2:
                    model.addAttribute("tuesday", secondCase);
                    break;
                case 3:
                    model.addAttribute("tuesday", thirdCase);
                    break;
            }
            int wednesday = day.getWednesday();
            switch(wednesday) {
                case 1:
                    model.addAttribute("wednesday", firstCase);
                    break;
                case 2:
                    model.addAttribute("wednesday", secondCase);
                    break;
                case 3:
                    model.addAttribute("wednesday", thirdCase);
                    break;
            }
            int thursday = day.getThursday();
            switch(thursday) {
                case 1:
                    model.addAttribute("thursday", firstCase);
                    break;
                case 2:
                    model.addAttribute("thursday", secondCase);
                    break;
                case 3:
                    model.addAttribute("thursday", thirdCase);
                    break;
            }
            int friday = day.getFriday();
            switch(friday) {
                case 1:
                    model.addAttribute("friday", firstCase);
                    break;
                case 2:
                    model.addAttribute("friday", secondCase);
                    break;
                case 3:
                    model.addAttribute("friday", thirdCase);
                    break;
            }
        }

        model.addAttribute("empno", empno);
        model.addAttribute("month", month);
        model.addAttribute("year", year);

        return "schedule/showSimpleSchedule";
    }

    //스케줄 신청 조회
    @GetMapping("/showScheduleRequest")
    public String showScheduleRequest(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long)session.getAttribute("user");
        int empno = num.intValue();

        List<ScheduleRequest> scheduleRequestList = scheduleService.scheduleRequestList(empno);
        model.addAttribute("scheduleRequestList", scheduleRequestList);
        model.addAttribute("empno", empno);
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

    //모든 달 스케줄 신청하기
    @GetMapping("/updateSchedule")
    public String updateSchedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        model.addAttribute("empno", empno);
        model.addAttribute("currentYear", currentYear);
        return "schedule/updateSchedule";
    }
    @PostMapping("/updateSchedule")
    public String updateSchedule(HttpServletRequest request,
                                 @RequestParam("monday") int monday, @RequestParam("tuesday") int tuesday,
                                 @RequestParam("wednesday") int wednesday, @RequestParam("thursday") int thursday,
                                 @RequestParam("friday") int friday, @RequestParam("year") int year,
                                 @RequestParam("month") int month){

        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        try {
            ScheduleRequest scheduleRequest = scheduleService.checkDuplicate(empno, year, month);
            if(scheduleRequest.getEmpno()==empno && scheduleRequest.getYear()==year && scheduleRequest.getMonth()==month) {
                return "schedule/error";
            } else {
                scheduleService.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
                return "redirect:/schedule";
            }
        } catch (NullPointerException e) {
            scheduleService.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
            return "redirect:/schedule";
        }
    }

    //스케줄신청조회(어드민용)
    @GetMapping("/manageScheduleRequest")
    public String manageScheduleRequest(Model model) {
        log.info(scheduleService.ScheduleList());

        return "schedule/error";
    }
}
