package com.pro.project.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCommand {

    private Long empno;
    @Getter
    private String password;
    private boolean rememberEmpno;

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(Long Empno) {
        this.empno = Empno;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getRememberEmpno() {
        return rememberEmpno;
    }
    public boolean isRememberEmpno(boolean rememberEmpno) {
        return rememberEmpno;
    }


}
