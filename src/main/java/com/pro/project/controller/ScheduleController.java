package com.pro.project.controller;

import com.pro.project.dto.Day;
import com.pro.project.dto.ScheduleRequest;
import com.pro.project.service.ScheduleService;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    //스케줄 신청 조회
    @GetMapping("/showScheduleRequest")
    public String showScheduleRequest(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long)session.getAttribute("user");
        int empno = num.intValue();

        //이번달 스케줄 조회
        List<ScheduleRequest> scheduleRequestList = scheduleService.scheduleRequestList(empno);
        model.addAttribute("scheduleRequestList", scheduleRequestList);
        model.addAttribute("empno", empno);

        //신청조회
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        String firstCase = "8시출근 - 17시퇴근";
        String secondCase = "9시출근 - 18시퇴근";
        String thirdCase = "10시출근 - 19시퇴근";

        //현재 시간에 대하여.
        Day day = scheduleService.selectOneDayCheck(empno, year, month);

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

        String empName = scheduleService.getName(empno);
        model.addAttribute("name", empName);
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

    //모든 달 스케줄 신청하기
    @GetMapping("/updateSchedule")
    public String updateSchedule(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();
        String name = scheduleService.getName(empno);
        log.info(name+"======================================");
        model.addAttribute("name", name);

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
                                 @RequestParam("month") int month, Model model){

        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        try {
            ScheduleRequest scheduleRequest = scheduleService.checkDuplicate(empno, year, month);
            if(scheduleRequest.getEmpno()==empno && scheduleRequest.getYear()==year && scheduleRequest.getMonth()==month) {
                return "schedule/error";
            } else {
                scheduleService.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
                return "redirect:/showScheduleRequest";
            }
        } catch (NullPointerException e) {
            scheduleService.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
            return "redirect:/showScheduleRequest";
        }
    }
    @PostMapping("/changeSchedule")
    public String changeSchedule(HttpServletRequest request,
                                 @RequestParam("monday") int monday, @RequestParam("tuesday") int tuesday,
                                 @RequestParam("wednesday") int wednesday, @RequestParam("thursday") int thursday,
                                 @RequestParam("friday") int friday, @RequestParam("year") int year,
                                 @RequestParam("month") int month) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        scheduleService.changeSchedule(monday, tuesday, wednesday, thursday, friday, empno, year, month);
        return "redirect:/showScheduleRequest";
    }

    //스케줄신청조회(어드민용)
    @GetMapping("/manageScheduleRequest")
    public String manageScheduleRequest(Model model) {
        log.info(scheduleService.ScheduleList());
        List<ScheduleRequest> scheduleList = scheduleService.ScheduleList();
        model.addAttribute("scheduleList", scheduleList);
        return "schedule/manageScheduleRequest";
    }

    //어드민 신청 조회 중 해당 데이터만 accept로 만들기
    @PostMapping("/acceptSchedule")
    public String acceptSchedule(@RequestParam String accept, @RequestParam int empno, @RequestParam int year, @RequestParam int month) {
        scheduleService.acceptSchedule(accept, empno, year, month);
        return "redirect:/manageScheduleRequest";
    }

    @PostMapping("/applySchedule")
    public String applySchedule(HttpServletRequest request, @RequestParam int monday, @RequestParam int tuesday,
                                @RequestParam int wednesday, @RequestParam int thursday, @RequestParam int friday,
                                @RequestParam int year, @RequestParam int month){
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();

        //1. 승인된 데이터에서 바로 daycheck 인서트.
        if(scheduleService.selectOneDayCheck(empno, year, month)==null) {
            scheduleService.insertSchedule(empno, monday, tuesday, wednesday, thursday, friday, year, month);
            scheduleService.rejectCheck(empno, year, month);
            return "redirect:/showScheduleRequest";
        } else {
            return "schedule/error";
        }
        //2. scheduleRequest에 있던 데이터는 삭제하기
    }

    @PostMapping("/rejectSchedule")
    public String rejectSchedule(@RequestParam("empno") int empno, @RequestParam("year") int year, @RequestParam("month") int month) {
        scheduleService.acceptSchedule("reject", empno, year, month);
        return "redirect:/manageScheduleRequest";
    }

    @PostMapping("/rejectCheck")
    public String rejectCheck(HttpServletRequest request,
                              @RequestParam("year") int year, @RequestParam("month") int month) {
        HttpSession session = request.getSession();
        Long num = (Long) session.getAttribute("user");
        int empno = num.intValue();
        scheduleService.rejectCheck(empno, year, month);

        return "redirect:/showScheduleRequest";
    }

    @GetMapping("/registerpage")
    public String empRegist(){
        return "schedule/registerpage";
    }

    @PostMapping("/registeremp")
    public String registeremp(@RequestParam("Dept")String dept,@RequestParam("empno") int empno,
                              @RequestParam("password") String password,@RequestParam("name") String name,
                              @RequestParam("job")String job,@RequestParam("sal")int sal,
                              @RequestParam("addr") String addr, @RequestParam("email") String email,
                              @RequestParam("phone") String phone ){

        int deptno=0;
        String location="";
        switch (dept){
            case "영업팀":deptno=1100;
            location="3F";
            break;
            case "전산팀":deptno=1600;
                location="4F";
            break;
            case "인사팀":deptno=1800;
                location="5F";
            break;
            case "생산팀":deptno=2220;
                location="B1F";
            break;
        }

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int month = today.getMonthValue();
        int year = today.getYear();
        String lastday = today.format(sdf);

        try {
            scheduleService.registeremp1(empno,job,sal,name,"view",deptno,addr,email,password,phone);
        //사번 중복 검사
        } catch (Exception e){
            return "schedule/registerError";
        }
        scheduleService.registeremp2(empno,year,month);
        scheduleService.registeremp3(empno,deptno,location,dept,name);
        scheduleService.registeremp4(empno,month);
        scheduleService.registeremp6(empno,lastday);

     return "schedule/registerpage";
    }


}
