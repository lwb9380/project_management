package com.pro.project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DeptDto {
    private Long empno;         //사원
    private Long deptno;
    private String location;
    private String deptname;
    private String name;
    private String isworking;
}
