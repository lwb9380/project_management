package com.pro.project.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long empno;         //사원
    private Long deptno;        //부서번호
    private String location;    //위치
    private String deptname;    //부서명
    private String name;        //이름
    private String isworking;  // 출근,출근전
}
