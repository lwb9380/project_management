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
}
