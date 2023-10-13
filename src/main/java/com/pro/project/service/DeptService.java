package com.pro.project.service;

import com.pro.project.entity.Dept;
import com.pro.project.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;
     //직원들 총합
    public Long countTotal(Long deptno){return deptRepository.countTotal(deptno);}
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


    public  List<Dept> findAll() {
        return deptRepository.findAll();
    }
    //---------------------------------------------------------------------------------
    public Long countWorkByDeptNo1100(Long deptno) {
        return deptRepository.countWork1100(deptno);
    }

    public Long countPreWorkByDeptNo1100(Long deptno) {
        return deptRepository.countPreWork1100(deptno);
    }
    public Long countLeaveWorkByDeptNo1100(Long deptno) {
        return deptRepository.countLeave1100(deptno);
    }
    public Long countCommuteByDeptNo1100(Long deptno) {
        return deptRepository.countCommute1100(deptno);
    }
    public Long countVacationByDeptNo1100(Long deptno) {
        return deptRepository.countVacation1100(deptno);
    }
    public String findDeptnameByDeptno(Long deptno){
        return  deptRepository.findDeptnameByDeptno(deptno);
    }

    //---------------------------------------------------------------------------------
    public Long countPreWorkByDeptNo1200(Long deptno) {
        return deptRepository.countPreWork1200(deptno);
    }
    public Long countWorkByDeptNo1200(Long deptno) {
        return deptRepository.countWork1200(deptno);
    }
    public Long countLeaveWorkByDeptNo1200(Long deptno) {
        return deptRepository.countLeave1200(deptno);
    }
    public Long countCommuteByDeptNo1200(Long deptno) {
        return deptRepository.countCommute1200(deptno);
    }
    public Long countVacationByDeptNo1200(Long deptno) {
        return deptRepository.countVacation1200(deptno);
    }
    //---------------------------------------------------------------------------------


}

