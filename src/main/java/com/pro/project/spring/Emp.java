package com.pro.project.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor //자동생성기
@Getter
@Setter
@ToString
public class Emp {

    private Long empno;
    private String password;
    private String email;
    private String empname;
    private String phone;
    private String addr;
    private Long deptno;

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}

