package com.pro.project.repository;


import com.pro.project.entity.Emp;
import com.pro.project.entity.VacationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {
    Page<VacationRequest> findAll(Pageable pageable);
}
