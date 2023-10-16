package com.pro.project.controller;

import com.pro.project.entity.*;
import com.pro.project.repository.DeptRepository;
import com.pro.project.repository.EmpRepository;
import com.pro.project.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;


    private final EmpRepository empRepository;

    @Autowired
    private DeptRepository deptRepository;


    @Autowired
    private HttpSession session;  // HttpSession을 주입받습니다.

    @Autowired
    public VacationRequestController(
            VacationRequestService vacationRequestService, EmpRepository empRepository) {
        this.vacationRequestService = vacationRequestService;

        this.empRepository = empRepository;
    }

    @GetMapping("/vacationRequest")
    public String showVacationRequestForm(Model model, Principal principal) {
        Long loggedInEmpno = (Long) session.getAttribute("user"); // 세션에서 empno 가져옴

        if (loggedInEmpno == null) {
            // 로그인하지 않았다면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        model.addAttribute("vacationRequest", new VacationRequest());




        // 로그인한 사용자의 정보를 가져올 때 사용
        Emp loggedInEmp = empRepository.findById(loggedInEmpno).orElse(null);
        if (loggedInEmp != null) {
            model.addAttribute("loggedInEmp", loggedInEmp);

            // 로그인한 사용자의 empname 정보를 가져옴
            String loggedInName = loggedInEmp.getName(); // 이 부분은 Emp 엔터티의 구조에 따라서 적절한 메서드를 호출
            model.addAttribute("loggedInName", loggedInName);




            // 로그인한 사용자의 deptno 정보를 가져올 때 사용

            Long loggedInDeptno = loggedInEmp.getDeptno();  // 이 부분은 Emp 엔터티에 따라 변경될 수 있음
            model.addAttribute("loggedInDeptno", loggedInDeptno);
            model.addAttribute("loggedInEmpno", loggedInEmpno);





        }

        List<Emp> deptno = empRepository.findAll();
        model.addAttribute("deptno", deptno);

        List<Emp> empno = empRepository.findAll();
        model.addAttribute("empno", empno);

        List<VacationRequest> vacationRequests = vacationRequestService.getAllVacationRequests();
        model.addAttribute("vacationRequests", vacationRequests);

        return "vacationRequestForm";
    }

    @PostMapping("/submit")
    public String submitVacationRequest(@ModelAttribute VacationRequest vacationRequest) {

        try {
            LocalDateTime currentDatetime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

            // 현재 시간을 휴가 신청일로 설정하고 원하는 형식으로 변환
            vacationRequest.setRequestDatetime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
            Long loggedInEmpno = (Long) session.getAttribute("user"); // 세션에서 empno 가져옴
            if (loggedInEmpno == null) {
                // 로그인하지 않았다면 로그인 페이지로 리다이렉트
                return "redirect:/login";
            }

            // 휴가 신청 서비스를 호출하여 데이터 저장 로직 추가
            vacationRequestService.submitVacationRequest(vacationRequest);

            return "redirect:/start";
        } catch (Exception e) {
            // 예외 처리 코드 추가
            return "redirect:/vacationRequest?error=true";
        }
    }

    @GetMapping("/vacationRequests")
    public String showVacationRequests(Model model) {
        List<VacationRequest> vacationRequests = vacationRequestService.getAllVacationRequests();
        model.addAttribute("vacationRequests", vacationRequests);
        return "vacationRequestList";
    }
    @GetMapping("/api/vacation-requests")
    @ResponseBody
    public Page<VacationRequest> getVacationRequests(Pageable pageable) {
        return vacationRequestService.getAllVacationRequests(pageable);
    }

}
