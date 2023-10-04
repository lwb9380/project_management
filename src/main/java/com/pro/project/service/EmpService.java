package com.pro.project.service;

import com.pro.project.entity.Emp;
import com.pro.project.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
    private final EmpRepository empRepository;

    @Autowired
    public EmpService(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    public void insertEmp(Emp emp) {
        empRepository.save(emp);
    }
    public Emp selectByEmpno(Long empno) {
        return empRepository.findByEmpno(empno);
    }
}
