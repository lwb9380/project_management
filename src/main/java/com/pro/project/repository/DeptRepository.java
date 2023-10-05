package com.pro.project.repository;

import com.pro.project.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '출근전'")
        //[출근전] 카운트
    Long countPreWork(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '출근'")
        //[출근] 카운트
    Long countWork(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '퇴근'")
        //[퇴근] 카운트
    Long countLeave(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '외출'")
        //[외출] 카운트
    Long countCommute(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '휴가'")
        //[휴가] 카운트
    Long countVacation(@Param("deptno") Long deptno);

    @Query("select d.deptname from Dept d where d.deptno = :deptno group by d.deptno, d.deptname")
        // deptno 를 이용해 deptname 이름을 가져오는 코드
    String findDeptnameByDeptno(@Param("deptno") Long deptno);
}






