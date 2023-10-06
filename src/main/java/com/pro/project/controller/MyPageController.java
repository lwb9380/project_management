package com.pro.project.controller;

import com.pro.project.dto.VacationDto;
import com.pro.project.entity.Emp;
import com.pro.project.repository.MyPageRepository;
import com.pro.project.service.MailService;
import com.pro.project.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    private MyPageRepository myPageRepository;

    @Autowired
    private StuService stuService;
    public MyPageController(MyPageRepository myPageRepository) {
        this.myPageRepository = myPageRepository;
    }

    @Autowired
    private MailService mailService;
    @Autowired
    private HttpSession session;

    @GetMapping("page")
    public String MyPage(Model model) {
        List<Emp> myPageInfoList = myPageRepository.findAll();
        model.addAttribute("list",myPageInfoList);
    return "page";
    }

    @GetMapping("/Vacationdept")
    public String showrequest(Model model){
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();
        int deptno=stuService.getDeptNo(empno);

        List<VacationDto> list=stuService.getVacationRequest(deptno);

        model.addAttribute("list",list);

        return "showrequest";
    }

    @PostMapping("/repulse")
    public String repulse(@RequestParam("id")int id){
        VacationDto dto=stuService.getvacationone(id);
        int empno=dto.getEmpno();
        mailService.sendMail("휴가 신청이 반려되었습니다", LocalDateTime.now(), empno, "관리자", "휴가 신청 결과");
        stuService.deleteVacationRequest(id);

        return "hhhh";
    }


    @PostMapping("/admitVacation")
    public String admitvaca(@RequestParam("id")int id, @RequestParam("lastday")String lastday){
        VacationDto dto=stuService.getvacationone(id);
        int empno=dto.getEmpno();
        LocalDate startday=dto.getVacation_hope_date();


        Long number=(Long)session.getAttribute("user");


        if (dto.getDetailed_info().equals("오전 반차")){
             int month=startday.getMonthValue();
            String day="day"+startday.getDayOfMonth();
             stuService.harftime(day,empno,month,"오전 반차");
            mailService.sendMail("반차 신청이 승인되었습니다", LocalDateTime.now(), empno, "관리자", "휴가 신청 결과");
            stuService.deleteVacationRequest(id);
        } else if(dto.getDetailed_info().equals("오후 반차")){
            int month=startday.getMonthValue();
            String day="day"+startday.getDayOfMonth();
            stuService.harftime(day,empno,month,"오후 반차");
            mailService.sendMail("반차 신청이 승인되었습니다", LocalDateTime.now(), empno, "관리자", "휴가 신청 결과");
            stuService.deleteVacationRequest(id);
        }




        int days=Integer.parseInt(dto.getVacation_period());
        for(int i=0;i<days;i++){
            int month=startday.getMonthValue();
            String day="day"+startday.getDayOfMonth();

            startday=startday.plusDays(1);
            stuService.updateDayVacation(day,empno,month);
        }

        mailService.sendMail("휴가 신청이 승인되었습니다", LocalDateTime.now(), empno, "관리자", "휴가 신청 결과");
        stuService.deleteVacationRequest(id);

    return "hhhh";
    }


}
