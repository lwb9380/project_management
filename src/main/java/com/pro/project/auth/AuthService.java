package com.pro.project.auth;

import com.pro.project.entity.Emp;
import com.pro.project.repository.EmpRepository;
import com.pro.project.spring.WrongIdPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private final EmpRepository empRepository;

	@Autowired
	public AuthService(EmpRepository empRepository) {
		this.empRepository = empRepository;
	}

	public boolean authenticate(Long empno, String password) {
		Emp emp = empRepository.findByEmpno(empno);
		if (emp == null) {
			throw new WrongIdPasswordException();
		}
		if (!emp.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return true;
	}
}
