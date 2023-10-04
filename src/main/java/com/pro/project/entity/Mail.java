package com.pro.project.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "Mail")
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, nullable = true)
    private String sName;

    @Column(length=100, nullable = true)
    private String title;

    @Column(length=200, nullable = true)
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(length = 255, nullable = false)
    private int empno;
}
