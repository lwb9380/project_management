package com.pro.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;


    // 다른 필드 및 생성자, getter, setter 등 추가 가능

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // 연관 관계 매핑
//    @OneToMany(mappedBy = "employee")
//    private List<VacationRequest> vacationRequests;





    // 생성자, getter 및 setter 메서드 생략
}
