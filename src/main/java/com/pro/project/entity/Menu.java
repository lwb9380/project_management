package com.pro.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "menu")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = true)
    private Integer year;

    @Column(length = 50, nullable = true)
    private Integer month;

    @Column(length = 50, nullable = true)
    private Integer day;

//    @Enumerated(EnumType.ORDINAL)
    @Column(length = 50, nullable = true)
//    private DayOfWeek dw;
    private int dw;

    @Column(length = 50, nullable = true)
    private String menu;

    @Column(length = 50, nullable = true)
    private String menu1;

    @Column(length = 50, nullable = true)
    private String menu2;

    @Column(length = 50, nullable = true)
    private String menu3;

    @Column(length = 50, nullable = true)
    private String menu4;



    @Column(name = "week_num", length = 50, nullable = true)
    private Integer weekNum;
}