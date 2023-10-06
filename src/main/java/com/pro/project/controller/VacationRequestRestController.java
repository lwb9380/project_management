package com.pro.project.controller;

import com.pro.project.entity.VacationRequest;
import com.pro.project.service.VacationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacation-requests")
public class VacationRequestRestController {

    private final VacationRequestService vacationRequestService;

    @Autowired
    public VacationRequestRestController(VacationRequestService vacationRequestService) {
        this.vacationRequestService = vacationRequestService;
    }

    @GetMapping("/all")
    public List<VacationRequest> getAllVacationRequests() {
        // 휴가 신청 목록을 서비스에서 가져와서 반환

        return vacationRequestService.getAllVacationRequests();
    }
}
