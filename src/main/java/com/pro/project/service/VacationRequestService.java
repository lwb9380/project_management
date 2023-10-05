package com.pro.project.service;

import com.pro.project.dto.VacationDto;
import com.pro.project.entity.Emp;
import com.pro.project.entity.VacationRequest;
import com.pro.project.repository.EmpRepository;
import com.pro.project.repository.VacationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VacationRequestService {


    private EmpRepository empRepository;
    private final VacationRequestRepository vacationRequestRepository;

    @Autowired
    public VacationRequestService(EmpRepository empRepository, VacationRequestRepository vacationRequestRepository) {
        this.empRepository = empRepository;
        this.vacationRequestRepository = vacationRequestRepository;
    }

    public VacationRequest saveVacationRequest(VacationRequest request) {
        // 폼 데이터를 받아와서 저장
        return vacationRequestRepository.save(request);
    }

    public void submitVacationRequest(VacationRequest vacationRequest) {
        vacationRequestRepository.save(vacationRequest);
    }

    public List<VacationRequest> getAllVacationRequests() {

        return vacationRequestRepository.findAll();
    }
    public Page<VacationRequest> getAllVacationRequests(Pageable pageable) {
        return vacationRequestRepository.findAll(pageable);
    }



}
