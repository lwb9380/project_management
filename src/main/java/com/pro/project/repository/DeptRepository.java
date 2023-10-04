package com.pro.project.repository;

import com.pro.project.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
    //        @Query(value = "SELECT COUNT(*) FROM dept WHERE deptno = 1100 AND isworking = '출근전'", nativeQuery = true)
//    public int countDept();
//    @Query(value = "SELECT COUNT(*) FROM dept WHERE deptno = 1100 AND isworking = '출근'", nativeQuery = true)
//    public int countDept2();
//    @Query(value = "SELECT COUNT(*) FROM dept WHERE deptno = 1100 AND isworking = '퇴근'", nativeQuery = true)
//    publ
    // public List<Dept> findAll();
    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '출근전'")  //[출근전] 카운트
    Long countPreWork(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '출근'")    //[출근] 카운트
    Long countWork(@Param("deptno") Long deptno);

    @Query("SELECT COUNT(d) FROM Dept d WHERE d.deptno = :deptno AND d.isworking = '퇴근'")   //[퇴근] 카운트
    Long countLeave(@Param("deptno") Long deptno);

    //    List<Dept> findByDeptno(Long DeptNo);

//    @Query("SELECT d.deptname FROM Dept d WHERE d.deptno = :deptno")
@Query("select d.deptname from Dept d where d.deptno = :deptno group by d.deptno, d.deptname")  // deptno 를 이용해 deptname 이름을 가져오는 코드
    String findDeptnameByDeptno(@Param("deptno") Long deptno);
}






