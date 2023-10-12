package com.pro.project.dao;

import com.pro.project.dto.Day;
import com.pro.project.dto.Dept;
import com.pro.project.dto.ScheduleRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleDao {

    public void insertSchedule(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month);
    public Day selectDaycheck(int empno);
    public void updateSchedule(int monday, int tuesday, int wednesday, int thursday, int friday, int empno);
    public void insertScheduleRequest(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month);
    public ScheduleRequest selectRequest(int empno);
    public void deleteSchedule(int empno);
    public void deleteScheduleRequest(int empno);

    public ScheduleRequest checkDuplicate(int empno, int year, int month);

    public List<ScheduleRequest> scheduleRequestList(int empno);

    public Dept deptByEmpno(int empno);

    public List<ScheduleRequest> ScheduleList();

    public void registeremp1(int empno,String job, int sal, String name, String authority, int deptno, String addr, String email, String password, String phone);

    public void registeremp2(int empno,int year,int month );

    public void registeremp3(int empno, int deptno, String location, String deptname, String name);

    public void registeremp4(int empno, int month);

    public void registeremp5(int empno, int month);

    public void registeremp6(int empno, String lastday);

    public void acceptSchedule(String accept, int empno, int year, int month);
}
