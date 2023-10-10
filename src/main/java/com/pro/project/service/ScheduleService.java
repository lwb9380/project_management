package com.pro.project.service;

import com.pro.project.dao.ScheduleDao;
import com.pro.project.dto.Day;
import com.pro.project.dto.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleDao scheduleDao;

    @Autowired
    public ScheduleService(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    public void insertSchedule(int empno, int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int year, int month) {
        scheduleDao.insertSchedule(empno, 월요일, 화요일, 수요일, 목요일, 금요일, year, month);
    }

    public Day selectDaycheck(int empno) {
        return scheduleDao.selectDaycheck(empno);
    }

    public void updateSchedule(int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int empno) {
        scheduleDao.updateSchedule(월요일, 화요일, 수요일, 목요일, 금요일, empno);
    }

    public void insertScheduleRequest(int empno, int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int year, int month) {
        scheduleDao.insertScheduleRequest(empno, 월요일, 화요일, 수요일, 목요일, 금요일, year, month);
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

}
