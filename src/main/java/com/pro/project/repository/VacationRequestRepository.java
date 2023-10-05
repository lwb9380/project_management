package com.pro.project.repository;


import com.pro.project.entity.Emp;
import com.pro.project.entity.VacationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

}
