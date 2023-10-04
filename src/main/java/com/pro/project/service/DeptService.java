package com.pro.project.service;

import com.pro.project.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DeptRepository deptRepository;


    public Long countPreWorkByDeptNo(Long deptno) {
        return deptRepository.countPreWork(deptno);
    }

    public Long countWorkByDeptNo(Long deptno) {
        return deptRepository.countWork(deptno);
    }

    public Long countLeaveByDeptNo(Long deptno) {
        return deptRepository.countLeave(deptno);
    }

    public String getDeptnameByDeptno(Long deptno) {
        return deptRepository.findDeptnameByDeptno(deptno);
    }

}

