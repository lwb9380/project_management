package com.pro.project.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "emp")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empno;
    @Column(length = 50, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String addr;
    @Column(length = 50, nullable = false)
    private String phone;
    @Column(length = 50, nullable = false)
    private String empname;
    @Column(length = 50, nullable = false)
    private Long deptno;
    @Column(length = 50, nullable = false)
    private String email;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}