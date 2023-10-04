package com.pro.project.controller;

import com.pro.project.entity.Department;
import com.pro.project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/form")
    public String departmentForm() {
        return "departmentForm";
    }

    @PostMapping("/save")
    public String saveDepartment(Department department) {
        departmentRepository.save(department);
        return "redirect:/departments/list";
    }

    @GetMapping("/list")
    public String departmentList(Model model) {
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "departmentList";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
