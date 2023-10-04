package com.pro.project.controller;

import com.pro.project.entity.Department;
import com.pro.project.entity.Employee;
import com.pro.project.repository.DepartmentRepository;
import com.pro.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }



    @GetMapping("/form")
    public String employeeForm(Model model) {
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/list")
    public String employeeList(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employeeList";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @GetMapping("/byDepartment/{departmentId}")
    @ResponseBody
    public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId) {
        // departmentId를 기반으로 해당 부서의 사원 목록을 조회하여 반환
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees;
    }
}
