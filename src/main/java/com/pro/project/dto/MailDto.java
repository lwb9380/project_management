package com.pro.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class MailDto {

    private Long id;

    private String content;

    private LocalDateTime date;

    private int empno;

    private String sName;

    private String title;

}