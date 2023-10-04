package com.pro.project.service;

import com.pro.project.entity.Department;
import com.pro.project.entity.Employee;
import com.pro.project.repository.DepartmentRepository;
import com.pro.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // 새로운 사원 정보를 저장하는 메서드
    public void saveEmployee(String employeeName, Long departmentId) {
        // 존재하는 부서 정보 가져오기
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("부서를 찾을 수 없습니다."));

        // 새로운 Employee 객체 생성 및 설정
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setDepartment(department);

        // Employee 저장
        employeeRepository.save(employee);
    }

    // 다른 서비스 메서드 추가 가능
}
//