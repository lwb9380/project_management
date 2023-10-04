package com.pro.project.service;

import com.pro.project.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;

    //출근전
    public Long countPreWorkByDeptNo(Long deptno) {
        return deptRepository.countPreWork(deptno);
    }
    //출근
    public Long countWorkByDeptNo(Long deptno) {
        return deptRepository.countWork(deptno);
    }
    //퇴근
    public Long countLeaveByDeptNo(Long deptno) {
        return deptRepository.countLeave(deptno);
    }
    //외출
    public Long countCommuteByDeptNo(Long deptno) {
        return deptRepository.countCommute(deptno);
    }
    //휴가
    public Long countVacationByDeptNo(Long deptno) {
        return deptRepository.countVacation(deptno);
    }

    public String getDeptnameByDeptno(Long deptno) {
        return deptRepository.findDeptnameByDeptno(deptno);
    }

}

