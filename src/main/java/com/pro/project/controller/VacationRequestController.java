package com.pro.project.controller;

import com.pro.project.entity.Department;
import com.pro.project.entity.Employee;
import com.pro.project.entity.VacationRequest;
import com.pro.project.repository.DepartmentRepository;
import com.pro.project.repository.EmployeeRepository;
import com.pro.project.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class VacationRequestController {

    private final VacationRequestService vacationRequestService;
    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public VacationRequestController(
            VacationRequestService vacationRequestService,
            DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.vacationRequestService = vacationRequestService;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/vacationRequest")
    public String showVacationRequestForm(Model model) {
        model.addAttribute("vacationRequest", new VacationRequest());

        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);

        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", departments);

        List<VacationRequest> vacationRequests = vacationRequestService.getAllVacationRequests();
        model.addAttribute("vacationRequests", vacationRequests);

        return "vacationRequestForm";
    }

    @PostMapping("/submit")
    public String submitVacationRequest(@ModelAttribute VacationRequest vacationRequest) {
        try {
            LocalDateTime currentDatetime = LocalDateTime.now();

            // 현재 시간을 휴가 신청일로 설정하고 원하는 형식으로 변환
            vacationRequest.setRequestDatetime(LocalDateTime.now());

            // 휴가 신청 서비스를 호출하여 데이터 저장 로직 추가
            vacationRequestService.submitVacationRequest(vacationRequest);

            return "redirect:/vacationRequest";
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
}
