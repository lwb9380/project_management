package com.pro.project.service;

import com.pro.project.dao.ScheduleDao;
import com.pro.project.dto.Day;
import com.pro.project.dto.Dept;
import com.pro.project.dto.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleDao scheduleDao;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public void insertSchedule(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month) {
        scheduleDao.insertSchedule(empno, monday, tuesday, wednesday, thursday, friday, year, month);
    }

    public Day selectDaycheck(int empno) {
        return scheduleDao.selectDaycheck(empno);
    }

    public void updateSchedule(int monday, int tuesday, int wednesday, int thursday, int friday, int empno) {
        scheduleDao.updateSchedule(monday, tuesday, wednesday, thursday, friday, empno);
    }

    public void insertScheduleRequest(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month) {
        scheduleDao.insertScheduleRequest(empno, monday, tuesday, wednesday, thursday, friday, year, month);
    }

    public ScheduleRequest selectRequest(int empno) {
        return scheduleDao.selectRequest(empno);
    }

    public void deleteSchedule(int empno) {
        scheduleDao.deleteSchedule(empno);
    }

    public void deleteScheduleRequest(int empno) {
        scheduleDao.deleteScheduleRequest(empno);
    }

    public ScheduleRequest checkDuplicate(int empno, int year, int month) {
        return scheduleDao.checkDuplicate(empno, year, month);
    }

    public List<ScheduleRequest> scheduleRequestList(int empno) {
        List<ScheduleRequest> scheduleRequestList = scheduleDao.scheduleRequestList(empno);
        //Respect문 : 씨발 선우형 당신이 최고야
        scheduleRequestList.sort(Comparator.comparing(ScheduleRequest::getYear).thenComparing(ScheduleRequest::getMonth));
        return scheduleRequestList;
    }

    public Dept deptByEmpno(int empno) {
        return scheduleDao.deptByEmpno(empno);
    }

    public List<ScheduleRequest> ScheduleList() {
        List<ScheduleRequest> scheduleList = scheduleDao.ScheduleList();
        scheduleList.sort(Comparator.comparing(ScheduleRequest::getDeptno)
                .thenComparing(ScheduleRequest::getYear)
                .thenComparing(ScheduleRequest::getMonth));
        return scheduleList;
    }
    public void registeremp1(int empno,String job, int sal, String name, String authority, int deptno, String addr, String email, String password, String phone)
    {scheduleDao.registeremp1(empno, job, sal, name, authority, deptno, addr, email, password, phone);};

    public void registeremp2(int empno,int year,int month )
    {scheduleDao.registeremp2(empno,year,month);}

    public void registeremp3(int empno, int deptno, String location, String deptname, String name)
    {scheduleDao.registeremp3(empno,deptno,location,deptname,name);}

    public void registeremp4(int empno, int month)
    {scheduleDao.registeremp4(empno,month);
    scheduleDao.registeremp5(empno,month);}

    public void registeremp6(int empno, String lastday){
        scheduleDao.registeremp6(empno,lastday);
    }


    public void acceptSchedule(String accept, int empno, int year, int month) {
        scheduleDao.acceptSchedule(accept, empno, year, month);
    }
}
