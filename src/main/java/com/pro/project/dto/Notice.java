package com.pro.project.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice {

    private int id;
    private String title;
    private String content;
    private int deptno;

    private String witch;
}
