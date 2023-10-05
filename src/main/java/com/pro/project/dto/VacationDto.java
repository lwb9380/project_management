package com.pro.project.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VacationDto {

    private Long id;
    private int deptno;
    private String detailed_info;

    private int empno;
    private String request_datetime;
    private String sub_vacation_type;
    private LocalDate vacation_hope_date;

    private String vacation_period;
    private String vacation_reason;
    private String vacation_type;

    private String name;

}
