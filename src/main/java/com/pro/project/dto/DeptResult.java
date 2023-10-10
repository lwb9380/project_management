package com.pro.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeptResult {

    private int deptno;
    private int empno;
    private String name;
    private int month;
    private int tardy;
    private int worktime;
    private int extraworktime;


}
