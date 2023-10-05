package com.pro.project.service;

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

//    @Transactional
//    public void saveWithEmpInfo(VacationRequest vacationRequest, Long empno) {
//        Emp emp = empRepository.findById(empno).orElse(null); // Emp 조회
//        if (emp != null) {
//            vacationRequest.setEmpno(empno);
//            // deptno를 emp에서 가져오도록 수정
//            Long deptno = emp.getDeptno();
//            vacationRequest.setDeptno(deptno);  // 여기서 deptno를 emp에서 가져오는 것으로 가정했습니다.
//            vacationRequestRepository.save(vacationRequest);
//        } else {
//            // emp가 없는 경우의 예외 처리
//            throw new RuntimeException("Employee not found");
//        }
//    }
}
