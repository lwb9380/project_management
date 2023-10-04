package com.pro.project.repository;


import com.pro.project.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPageRepository extends JpaRepository<Emp, Long> {
    List<Emp> findAll();
}
