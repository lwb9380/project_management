package com.pro.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleRequest {
    private int empno;
    private int deptno;
    private int monday;
    private int tuesday;
    private int wednesday;
    private int thursday;
    private int friday;
    private int year;
    private int month;
    private String name;


    private String accept;
    private String cause;

}
