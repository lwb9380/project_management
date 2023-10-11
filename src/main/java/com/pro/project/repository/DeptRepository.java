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

    //--------------------------------------------------------------------------------------------------------------
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1100) AND d.isworking = '출근전'")
    Long countPreWork1100(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1100) AND d.isworking = '출근'")
    Long countWork1100(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1100) AND d.isworking = '퇴근'")
    Long countLeave1100(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1100) AND d.isworking = '외출'")
    Long countCommute1100(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1100) AND d.isworking = '휴가'")
    Long countVacation1100(@Param("deptno") Long deptno);


    //--------------------------------------------------------------------------------------------------------------
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1200) AND d.isworking = '출근전'")
    Long countPreWork1200(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1200) AND d.isworking = '출근'")
    Long countWork1200(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1200) AND d.isworking = '퇴근'")
    Long countLeave1200(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1200) AND d.isworking = '외출'")
    Long countCommute1200(@Param("deptno") Long deptno);
    @Query("SELECT COUNT(d) FROM Dept d WHERE (d.deptno = :deptno OR d.deptno = 1200) AND d.isworking = '휴가'")
    Long countVacation1200(@Param("deptno") Long deptno);


}






