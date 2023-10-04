package com.pro.project.auth;

import lombok.Getter;

public class AuthInfo {
@Getter
	private Long empno;
	@Getter
	private String password;
	@Getter
	private String email;
	@Getter
	private String empname;
	@Getter
	private String phone;
	@Getter
	private String addr;
	@Getter
	private Long deptno;


	public AuthInfo(Long empno, String password, String empname) {
		this.empno=empno;
		this.password=password;
		this.empname=empname;
	}

	public Long getEmpno() {
		return empno;
	}

	public String getPassword() {
		return password;
	}



	public String getEmpname() {
		return empname;
	}
}
